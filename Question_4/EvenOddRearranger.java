import java.util.Arrays;

public class EvenOddRearranger {

    // --- อัลกอริทึมที่ 1: Recursive Two-Pointer ---
    public static void rearrangeRecursive(int[] a, int left, int right) {
        // Base Case: เมื่อตัวชี้ชนกันหรือไขว้กัน
        if (left >= right) {
            return;
        }

        if (a[left] % 2 == 0) {
            // ซ้ายเป็นคู่ถูกต้องแล้ว ให้ขยับซ้าย
            rearrangeRecursive(a, left + 1, right);
        } else if (a[right] % 2 != 0) {
            // ขวาเป็นคี่ถูกต้องแล้ว ให้ขยับขวา
            rearrangeRecursive(a, left, right - 1);
        } else {
            // ซ้ายเป็นคี่ และ ขวาเป็นคู่ ให้สลับ (Swap)
            int temp = a[left];
            a[left] = a[right];
            a[right] = temp;
            
            // สลับเสร็จแล้ว ขยับทั้งสองข้าง
            rearrangeRecursive(a, left + 1, right - 1);
        }
    }

    // --- อัลกอริทึมที่ 2: Iterative Two-Pointer ---
    public static void rearrangeTwoPointer(int[] a) {
        int left = 0;
        int right = a.length - 1;

        while (left < right) {
            // เลื่อนซ้ายไปเรื่อยๆ ตราบใดที่ยังเจอจำนวนคู่
            while (left < right && a[left] % 2 == 0) {
                left++;
            }
            // เลื่อนขวาไปเรื่อยๆ ตราบใดที่ยังเจอจำนวนคี่
            while (left < right && a[right] % 2 != 0) {
                right--;
            }

            // ถ้า left ยังน้อยกว่า right แปลว่าเจอฝั่งซ้ายเป็นคี่ ฝั่งขวาเป็นคู่ จึงทำการสลับ
            if (left < right) {
                int temp = a[left];
                a[left] = a[right];
                a[right] = temp;
                
                left++;
                right--;
            }
        }
    }

    // --- อัลกอริทึมที่ 3: Extra Array ---
    public static int[] rearrangeExtraArray(int[] a) {
        int[] result = new int[a.length];
        int index = 0;

        // วนลูปรอบแรก เก็บเฉพาะจำนวนคู่
        for (int num : a) {
            if (num % 2 == 0) {
                result[index++] = num;
            }
        }

        // วนลูปรอบสอง เก็บเฉพาะจำนวนคี่
        for (int num : a) {
            if (num % 2 != 0) {
                result[index++] = num;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println("--- การทดสอบข้อมูลทั่วไป ---");
        int[] test1_1 = {7, 2, 9, 4, 1, 6, 3, 8};
        rearrangeRecursive(test1_1, 0, test1_1.length - 1);
        System.out.println("Recursive Two-Pointer: " + Arrays.toString(test1_1));

        int[] test1_2 = {7, 2, 9, 4, 1, 6, 3, 8};
        rearrangeTwoPointer(test1_2);
        System.out.println("Iterative Two-Pointer: " + Arrays.toString(test1_2));


        System.out.println("\n--- การทดสอบเพื่อเช็คว่าวิธีใดเป็น Stable Algorithm ---");
        // ข้อมูลนำเข้า [5, 2, 7, 4, 9, 6] 
        // ลำดับคู่เดิมคือ: 2, 4, 6 
        // ลำดับคี่เดิมคือ: 5, 7, 9
        // ผลลัพธ์แบบ Stable ควรจะได้: [2, 4, 6, 5, 7, 9]

        int[] test2_1 = {5, 2, 7, 4, 9, 6};
        rearrangeRecursive(test2_1, 0, test2_1.length - 1);
        System.out.println("Recursive Two-Pointer: " + Arrays.toString(test2_1) + " (NOT Stable)");

        int[] test2_2 = {5, 2, 7, 4, 9, 6};
        rearrangeTwoPointer(test2_2);
        System.out.println("Iterative Two-Pointer: " + Arrays.toString(test2_2) + " (NOT Stable)");

        int[] test2_3 = {5, 2, 7, 4, 9, 6};
        int[] resultExtra = rearrangeExtraArray(test2_3);
        System.out.println("Extra Array Method:    " + Arrays.toString(resultExtra) + " (STABLE ✅)");
    }
}