public class PairSumFinder {

    // --- อัลกอริทึมที่ 1: Brute Force ---
    public static boolean findPairBruteForce(int[] a, int k) {
        int n = a.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (a[i] + a[j] == k) {
                    System.out.println("Pair found: " + a[i] + " and " + a[j]);
                    return true;
                }
            }
        }
        return false;
    }

    // --- อัลกอริทึมที่ 2: Recursive Two-Pointer ---
    public static boolean findPairRecursive(int[] a, int k, int left, int right) {
        // Base Case: ไม่พบคำตอบ
        if (left >= right) {
            return false;
        }

        int sum = a[left] + a[right];

        if (sum == k) {
            System.out.println("Pair found: " + a[left] + " and " + a[right]);
            return true; // หากผลรวมเท่ากับ k ให้รายงานคู่ที่พบ[cite: 2]
        } else if (sum < k) {
            return findPairRecursive(a, k, left + 1, right); // หากผลรวมน้อยกว่า k ให้เพิ่มค่า left[cite: 2]
        } else {
            return findPairRecursive(a, k, left, right - 1); // หากผลรวมมากกว่า k ให้ลดค่า right[cite: 2]
        }
    }

    // --- อัลกอริทึมที่ 3: Binary Search ---
    public static boolean findPairBinarySearch(int[] a, int k) {
        for (int i = 0; i < a.length; i++) {
            int target = k - a[i]; // ค้นหาค่าเป้าหมายคือ k - A[i] ในสมาชิกที่เหลือ[cite: 2]
            
            // เรียกใช้ Binary Search มาตรฐาน
            int left = i + 1;
            int right = a.length - 1;
            
            while (left <= right) {
                int mid = left + (right - left) / 2;
                
                if (a[mid] == target) {
                    System.out.println("Pair found: " + a[i] + " and " + a[mid]);
                    return true;
                } else if (a[mid] < target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] A = {2, 4, 7, 11, 15, 20}; //[cite: 1]
        int k = 18; //[cite: 1]

        System.out.println("--- Brute Force ---");
        findPairBruteForce(A, k);

        System.out.println("\n--- Recursive Two-Pointer ---");
        findPairRecursive(A, k, 0, A.length - 1);

        System.out.println("\n--- Binary Search ---");
        findPairBinarySearch(A, k);
    }
}