public class PalindromeChecker {

    // เมธอดตัวช่วย (Helper) สำหรับล้างข้อมูลสตริงตาม "เงื่อนไขเพิ่มเติม" ของโจทย์
    // แปลงเป็นตัวเล็กทั้งหมด ลบช่องว่าง และลบเครื่องหมายวรรคตอน (เหลือแค่ตัวอักษรและตัวเลข)
    public static String cleanString(String s) {
        if (s == null) return "";
        return s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
    }

    // อัลกอริทึมที่ 1: Reverse and Compare
    public static boolean isPalindromeByReverse(String s) {
        String cleaned = cleanString(s);
        
        // ใช้ StringBuilder เพื่อกลับด้านสตริง
        String reversed = new StringBuilder(cleaned).reverse().toString();
        
        // เปรียบเทียบสตริงที่ทำความสะอาดแล้ว กับสตริงที่ถูกกลับด้าน
        return cleaned.equals(reversed);
    }

    // อัลกอริทึมที่ 2: Recursive Two-Pointer (เขียนตามชื่อเมธอดที่โจทย์สั่ง)
    public static boolean isPalindromeRecursive(String s, int left, int right) {
        // Base Case 1: ถ้า left ชนหรือเลย right แปลว่าตรวจสอบครบแล้ว และตรงกันหมด
        if (left >= right) {
            return true;
        }
        
        // Base Case 2: ตรวจพบว่าตัวอักษรซ้ายและขวาไม่เหมือนกัน (Early Exit หยุดทำงานทันที)
        if (s.charAt(left) != s.charAt(right)) {
            return false;
        }
        
        // Recursive Case: ถ้าตรงกัน ให้เรียกตัวเองเพื่อเช็คตัวถัดไปที่อยู่ถัดเข้าไปข้างใน
        return isPalindromeRecursive(s, left + 1, right - 1);
    }

    // เมธอดตัวช่วย (Wrapper) เพื่อความสะดวกในการเรียกใช้อัลกอริทึมที่ 2
    // หน้าที่คือทำความสะอาดสตริงก่อน แล้วค่อยส่งเข้า Recursive Two-Pointer
    public static boolean checkPalindromeRecursive(String s) {
        String cleaned = cleanString(s);
        if (cleaned.isEmpty()) return true;
        return isPalindromeRecursive(cleaned, 0, cleaned.length() - 1);
    }

    public static void main(String[] args) {
        // ชุดข้อมูลสำหรับการทดสอบ
        String[] testCases = {
            "racecar",
            "level",
            "algorithm",
            "gohangasalamiimalasagnahog",
            "A man, a plan, a canal: Panama" // เงื่อนไขเพิ่มเติม: มีอักษรพิมพ์ใหญ่/เล็ก, ช่องว่าง, วรรคตอน
        };

        System.out.println("--- ทดสอบ Palindrome ---");
        for (String test : testCases) {
            System.out.println("Input: \"" + test + "\"");
            
            // เรียกใช้วิธีที่ 1
            boolean result1 = isPalindromeByReverse(test);
            // เรียกใช้วิธีที่ 2 (ผ่าน Wrapper เพื่อทำความสะอาดสตริงก่อน)
            boolean result2 = checkPalindromeRecursive(test);
            
            System.out.println("Reverse output: " + result1);
            System.out.println("Recursive output: " + result2);
            System.out.println("------------------------");
        }
    }
}