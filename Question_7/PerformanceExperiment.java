public class PerformanceExperiment {

    // อัลกอริทึมจากข้อ 1: การกลับลำดับสตริง
    public static String reverseRecursive(String s) {
        if (s == null || s.isEmpty()) return s;
        return s.charAt(s.length() - 1) + reverseRecursive(s.substring(0, s.length() - 1));
    }

    public static String reverseIterative(String s) {
        if (s == null || s.isEmpty()) return s;
        StringBuilder sb = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }

    // อัลกอริทึมจากข้อ 6: ค้นหาคู่ผลรวมเท่ากับ k
    public static boolean findPairBruteForce(int[] a, int k) {
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[i] + a[j] == k) return true;
            }
        }
        return false;
    }

    public static boolean findPairRecursive(int[] a, int k, int left, int right) {
        if (left >= right) return false;
        int sum = a[left] + a[right];
        if (sum == k) return true;
        else if (sum < k) return findPairRecursive(a, k, left + 1, right);
        else return findPairRecursive(a, k, left, right - 1);
    }

    public static boolean findPairBinarySearch(int[] a, int k) {
        for (int i = 0; i < a.length; i++) {
            int target = k - a[i];
            int left = i + 1, right = a.length - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (a[mid] == target) return true;
                else if (a[mid] < target) left = mid + 1;
                else right = mid - 1;
            }
        }
        return false;
    }

    // เมธอดหลักสำหรับการรันทดสอบ
    public static void main(String[] args) {
        int[] sizes = {100, 1000, 10000, 100000}; // ขนาดข้อมูล 4 ระดับ[cite: 1]
        int runs = 5; // ทดลองขนาดละ 5 ครั้ง[cite: 2]

        System.out.println("========== เริ่มการทดลองเปรียบเทียบประสิทธิภาพ ==========\n");

        // การทดลองที่ 1: นำมาจากแบบฝึกหัดข้อ 1
        System.out.println(">>> ตารางผลการทดลอง: การกลับลำดับสตริง (ข้อ 1) <<<");
        System.out.printf("%-15s | %-25s | %-25s\n", "ขนาด (n)", "Algorithm 1 (Recursive)", "Algorithm 2 (Iterative)");
        System.out.println("-------------------------------------------------------------------------");

        for (int n : sizes) {
            // เตรียมข้อมูลทดสอบ
            StringBuilder temp = new StringBuilder();
            for (int i = 0; i < n; i++) temp.append("a");
            String data = temp.toString();

            long totalIterative = 0;
            long totalRecursive = 0;
            boolean recFailed = false;

            for (int r = 0; r < runs; r++) {
                // วัดเวลา Iterative
                long startIter = System.nanoTime(); 
                reverseIterative(data);
                long endIter = System.nanoTime(); 
                totalIterative += (endIter - startIter);

                // วัดเวลา Recursive (ใส่ try-catch ป้องกัน StackOverflow)
                try {
                    long startRec = System.nanoTime(); 
                    reverseRecursive(data);
                    long endRec = System.nanoTime(); 
                    totalRecursive += (endRec - startRec);
                } catch (StackOverflowError e) {
                    recFailed = true;
                }
            }

            String recResult = recFailed ? "FAILED (StackOverflow)" : (totalRecursive / runs) + " ns";
            System.out.printf("%-15d | %-25s | %-25d\n", n, recResult, (totalIterative / runs));
        }


        System.out.println("\n\n");


        // การทดลองที่ 2: นำมาจากแบบฝึกหัดข้อ 6
        System.out.println(">>> ตารางผลการทดลอง: ค้นหาคู่ผลรวม (ข้อ 6) <<<");
        System.out.printf("%-15s | %-25s | %-25s | %-25s\n", "ขนาด (n)", "Alg 1 (Brute Force)", "Alg 2 (Rec Two-Pointer)", "Alg 3 (Binary Search)");
        System.out.println("---------------------------------------------------------------------------------------------------");

        for (int n : sizes) {
            // เตรียมข้อมูลทดสอบ 
            int[] data = new int[n];
            for (int i = 0; i < n; i++) data[i] = i + 1;
            
            // ตั้งค่าเป้าหมายให้อยู่ท้ายสุด เพื่อบังคับให้ Brute Force ทำงานช้าสุด (Worst Case)
            int k = data[n - 2] + data[n - 1];

            long totalBrute = 0;
            long totalTwoPointer = 0;
            long totalBinary = 0;
            boolean twoPointerFailed = false;

            for (int r = 0; r < runs; r++) {
                // วัดเวลา Brute Force (ข้ามถ้านานเกินไป เพื่อไม่ให้คอมค้าง)
                if (n <= 10000) { 
                    long start1 = System.nanoTime(); //[cite: 1]
                    findPairBruteForce(data, k);
                    long end1 = System.nanoTime(); //[cite: 1]
                    totalBrute += (end1 - start1);
                }

                // วัดเวลา Binary Search
                long start2 = System.nanoTime(); //[cite: 1]
                findPairBinarySearch(data, k);
                long end2 = System.nanoTime(); //[cite: 1]
                totalBinary += (end2 - start2);

                // วัดเวลา Recursive Two-Pointer
                try {
                    long start3 = System.nanoTime(); //[cite: 1]
                    findPairRecursive(data, k, 0, data.length - 1);
                    long end3 = System.nanoTime(); //[cite: 1]
                    totalTwoPointer += (end3 - start3);
                } catch (StackOverflowError e) {
                    twoPointerFailed = true;
                }
            }

            // พิมพ์ค่าเฉลี่ย[cite: 2]
            String bruteResult = (n > 10000) ? "> 10,000,000,000 ns" : String.valueOf(totalBrute / runs);
            String tpResult = twoPointerFailed ? "FAILED (StackOverflow)" : String.valueOf(totalTwoPointer / runs);
            
            System.out.printf("%-15d | %-25s | %-25s | %-25d\n", n, bruteResult, tpResult, (totalBinary / runs));
        }
    }
}