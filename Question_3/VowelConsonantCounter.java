public class VowelConsonantCounter {

    // เมธอดสำหรับเช็คว่าตัวอักษรนั้นเป็นสระหรือไม่
    private static boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }

    // --- อัลกอริทึมที่ 1: Recursive Counting ---
    
    // เมธอดหลัก (Wrapper Method) ที่โจทย์กำหนด
    public static boolean hasMoreVowelsRecursive(String s) {
        if (s == null || s.isEmpty()) return false;
        // แปลงเป็นพิมพ์เล็กก่อนเพื่อไม่ให้ต้องแยกคิดพิมพ์เล็ก/พิมพ์ใหญ่
        return countHelper(s.toLowerCase(), 0, 0, 0);
    }

    // เมธอดตัวช่วย (Helper) เพื่อส่งค่าตัวแปรสะสมไปยังการเรียกครั้งถัดไป
    private static boolean countHelper(String s, int index, int vowels, int consonants) {
        // Base Case: เมื่อตรวจดูครบทุกตัวอักษรแล้ว
        if (index == s.length()) {
            return vowels > consonants;
        }

        char currentChar = s.charAt(index);

        // ตรวจสอบว่าเป็นตัวอักษรภาษาอังกฤษเท่านั้น (ละเว้นตัวเลข ช่องว่าง เครื่องหมายพิเศษ)
        if (Character.isLetter(currentChar)) {
            if (isVowel(currentChar)) {
                vowels++;
            } else {
                consonants++;
            }
        }

        // Recursive Case: ขยับ index ไปตัวถัดไป พร้อมส่งค่านับที่อัปเดตแล้วไปด้วย
        return countHelper(s, index + 1, vowels, consonants);
    }

    // --- อัลกอริทึมที่ 2: Iterative Counting ---
    public static boolean hasMoreVowelsIterative(String s) {
        if (s == null || s.isEmpty()) return false;
        
        String cleaned = s.toLowerCase();
        int vowels = 0;
        int consonants = 0;

        // ใช้ลูปอ่านข้อความทุกตัว
        for (int i = 0; i < cleaned.length(); i++) {
            char currentChar = cleaned.charAt(i);
            
            // ตรวจสอบเงื่อนไขละเว้น
            if (Character.isLetter(currentChar)) {
                if (isVowel(currentChar)) {
                    vowels++;
                } else {
                    consonants++;
                }
            }
        }

        return vowels > consonants;
    }

    // เมธอดสำหรับทดสอบการทำงาน
    public static void main(String[] args) {
        String[] testCases = {
            "education",            // สระ 5, พยัญชนะ 4 -> true
            "Hello World 123!",     // สระ 3, พยัญชนะ 7 -> false 
            "Rhythm",               // สระ 0, พยัญชนะ 6 -> false 
            "A E I O U"             // สระ 5, พยัญชนะ 0 -> true 
        };

        System.out.println("--- การทดสอบนับสระและพยัญชนะ ---");
        for (String test : testCases) {
            System.out.println("Input: \"" + test + "\"");
            System.out.println("Recursive Output: " + hasMoreVowelsRecursive(test));
            System.out.println("Iterative Output: " + hasMoreVowelsIterative(test));
            System.out.println("---------------------------");
        }
    }
}