public class StringReversal {

    // อัลกอริทึมที่ 1: Recursive Algorithm
    public static String reverseRecursive(String s) {
        // จัดการกรณีข้อมูลว่าง (Empty) หรือ null ป้องกันโปรแกรมหยุดทำงานผิดปกติ
        if (s == null || s.isEmpty()) {
            return s;
        }
        
        // นำตัวอักษรตัวสุดท้ายมาต่อกับผลลัพธ์จากการเรียกเมธอดกับสตริงส่วนที่เหลือ
        char lastChar = s.charAt(s.length() - 1);
        String remainingString = s.substring(0, s.length() - 1);
        
        return lastChar + reverseRecursive(remainingString);
    }

    // อัลกอริทึมที่ 2: Iterative Algorithm
    public static String reverseIterative(String s) {
        // จัดการกรณีข้อมูลว่าง
        if (s == null || s.isEmpty()) {
            return s;
        }
        
        // ใช้ StringBuilder เพื่อประสิทธิภาพที่ดีกว่าในการต่อสตริง
        StringBuilder sb = new StringBuilder();
        
        // ใช้ลูปเพื่ออ่านข้อความจากตำแหน่งสุดท้ายย้อนกลับไปยังตำแหน่งแรก
        for (int i = s.length() - 1; i >= 0; i--) {
            sb.append(s.charAt(i));
        }
        
        return sb.toString();
    }

    public static void main(String[] args) {
        // ทดสอบกรณีปกติ
        String test1 = "pots&pans"; // Input: pots&pans
        System.out.println("--- Test Normal Case ---");
        System.out.println("Original: " + test1);
        System.out.println("Recursive Output: " + reverseRecursive(test1)); // Output: snap&stop
        System.out.println("Iterative Output: " + reverseIterative(test1));
        
        // ทดสอบกรณีพิเศษ
        System.out.println("\n--- Test Edge Cases ---");
        System.out.println("Empty String Recursive: '" + reverseRecursive("") + "'");
        System.out.println("Null Iterative: " + reverseIterative(null));

        // ทดสอบกับสตริงขนาดประมาณ 10, 100, 1,000 และ 10,000 ตัวอักษร
        System.out.println("\n--- Test Large Strings ---");
        int[] sizes = {10, 100, 1000, 10000}; 
        
        for (int size : sizes) {
            // แก้ไข: ใช้ StringBuilder สร้างสตริงยาวๆ แทน .repeat() เพื่อให้รองรับ Java ทุกเวอร์ชัน
            StringBuilder tempStr = new StringBuilder();
            for (int j = 0; j < size; j++) {
                tempStr.append("a");
            }
            String largeStr = tempStr.toString();
            
            // จับเวลา Iterative
            long startIterative = System.nanoTime();
            reverseIterative(largeStr);
            long endIterative = System.nanoTime();
            
            // จับเวลา Recursive
            long startRecursive = System.nanoTime();
            try {
                reverseRecursive(largeStr);
                long endRecursive = System.nanoTime();
                System.out.println("Length " + size + " -> Iterative: " + (endIterative - startIterative) + " ns, Recursive: " + (endRecursive - startRecursive) + " ns");
            } catch (StackOverflowError e) {
                System.out.println("Length " + size + " -> Iterative: " + (endIterative - startIterative) + " ns, Recursive: FAILED (StackOverflowError)");
            }
        }
    }
}