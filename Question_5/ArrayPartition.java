import java.util.Arrays;

public class ArrayPartition {

    // --- อัลกอริทึมที่ 1: Recursive Partition ---
    public static void partitionRecursive(int[] a, int k, int left, int right) {
        // Base Case: หยุดเมื่อตัวชี้ชนกันหรือไขว้กัน
        if (left >= right) {
            return;
        }

        if (a[left] <= k) {
            // ฝั่งซ้ายน้อยกว่าหรือเท่ากับ k ถูกต้องแล้ว เลื่อนซ้ายไป
            partitionRecursive(a, k, left + 1, right);
        } else if (a[right] > k) {
            // ฝั่งขวามากกว่า k ถูกต้องแล้ว เลื่อนขวาเข้า
            partitionRecursive(a, k, left, right - 1);
        } else {
            // ซ้ายดันมากกว่า k และ ขวาดันน้อยกว่า/เท่ากับ k -> ทำการสลับที่
            int temp = a[left];
            a[left] = a[right];
            a[right] = temp;
            
            // สลับเสร็จ ขยับทั้งคู่แล้วเรียกซ้ำ
            partitionRecursive(a, k, left + 1, right - 1);
        }
    }

    // --- อัลกอริทึมที่ 2: Iterative Partition ---
    public static void partitionIterative(int[] a, int k) {
        int left = 0;
        int right = a.length - 1;

        while (left < right) {
            // เลื่อนหาตัวฝั่งซ้ายที่อยู่ผิดฝั่ง (มากกว่า k)
            while (left < right && a[left] <= k) {
                left++;
            }
            // เลื่อนหาตัวฝั่งขวาที่อยู่ผิดฝั่ง (น้อยกว่าหรือเท่ากับ k)
            while (left < right && a[right] > k) {
                right--;
            }

            // ถ้าเจอก็จับสลับกัน (In-place Swap)
            if (left < right) {
                int temp = a[left];
                a[left] = a[right];
                a[right] = temp;
                left++;
                right--;
            }
        }
    }

    // --- อัลกอริทึมที่ 3: Sorting-Based Algorithm ---
    public static void partitionBySorting(int[] a, int k) {
        // เรียงลำดับจากน้อยไปมาก
        // เมื่อเรียงเสร็จ ข้อมูลที่ <= k จะไปอยู่ข้างหน้าโดยอัตโนมัติ
        Arrays.sort(a);
    }

    public static void main(String[] args) {
        int k = 8;
        
        System.out.println("--- ทดสอบแบ่งกลุ่มอาร์เรย์ (k = " + k + ") ---");
        
        // ทดสอบ Recursive
        int[] arr1 = {12, 4, 7, 15, 3, 10, 8};
        System.out.println("Input:              " + Arrays.toString(arr1));
        partitionRecursive(arr1, k, 0, arr1.length - 1);
        System.out.println("Recursive Output:   " + Arrays.toString(arr1));

        // ทดสอบ Iterative
        int[] arr2 = {12, 4, 7, 15, 3, 10, 8};
        partitionIterative(arr2, k);
        System.out.println("Iterative Output:   " + Arrays.toString(arr2));

        // ทดสอบ Sorting-based
        int[] arr3 = {12, 4, 7, 15, 3, 10, 8};
        partitionBySorting(arr3, k);
        System.out.println("Sorting Output:     " + Arrays.toString(arr3));
    }
}