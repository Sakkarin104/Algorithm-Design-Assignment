# ข้อ 3 การเปรียบเทียบจำนวนสระและพยัญชนะ

### 1. คำอธิบายแนวคิดของอัลกอริทึมแต่ละวิธี
* **อัลกอริทึมที่ 1 (Recursive Counting):** ใช้วิธีเรียกตัวเองซ้ำ (Recursion) เพื่อตรวจสอบตัวอักษรทีละตัวจากซ้ายไปขวา โดยอาศัยเมธอดตัวช่วย (Helper Method) ในการส่งผ่านค่าดัชนีปัจจุบัน (Index) รวมถึงจำนวนสระและพยัญชนะสะสมไปยังการเรียกครั้งถัดไป 
  * **Base Case (กรณีฐาน):** `if (index == s.length())` เมื่ออ่านตัวอักษรครบทุกตัวแล้ว ให้เปรียบเทียบและคืนค่าว่าจำนวนสระมากกว่าพยัญชนะหรือไม่ (`vowels > consonants`)
  * **Recursive Case (กรณีเวียนเกิด):** นำตัวอักษรปัจจุบันมาตรวจสอบ หากเป็นตัวอักษรภาษาอังกฤษ ให้แยกเป็นสระหรือพยัญชนะแล้วบวกค่าเพิ่ม จากนั้นเรียกเมธอดตัวเองซ้ำโดยขยับ `index + 1`
* **อัลกอริทึมที่ 2 (Iterative Counting):** ใช้ลูป `for` เพื่ออ่านข้อความตั้งแต่ตัวแรกจนถึงตัวสุดท้ายทีละตัว มีตัวแปรนับจำนวนสระและพยัญชนะแยกกัน หากพบสระให้เพิ่มค่าตัวนับสระ หากพบพยัญชนะให้เพิ่มค่าตัวนับพยัญชนะ เมื่อจบลูปจึงนำมาเปรียบเทียบกัน

*(หมายเหตุ: ทั้งสองอัลกอริทึมถูกออกแบบให้เพิกเฉยต่อตัวเลข ช่องว่าง และเครื่องหมายพิเศษ โดยตรวจสอบเฉพาะตัวอักษรภาษาอังกฤษเท่านั้น และแปลงเป็นตัวพิมพ์เล็กก่อนการตรวจสอบเพื่อให้ตัวพิมพ์เล็กและพิมพ์ใหญ่มีค่าเท่ากัน)*

### 2. Pseudocode หรือผังขั้นตอนการทำงาน
**Algorithm 1: Recursive Counting**
```text
FUNCTION hasMoreVowelsRecursive(s)
    cleaned_s = CONVERT TO LOWERCASE(s)
    RETURN countHelper(cleaned_s, 0, 0, 0)
END FUNCTION

FUNCTION countHelper(s, index, vowels, consonants)
    IF index == LENGTH(s) THEN
        RETURN vowels > consonants
    END IF
    
    char = s[index]
    IF char IS ENGLISH LETTER THEN
        IF char IS VOWEL THEN
            vowels = vowels + 1
        ELSE
            consonants = consonants + 1
        END IF
    END IF
    
    RETURN countHelper(s, index + 1, vowels, consonants)
END FUNCTION
```

**Algorithm 2: Iterative Counting**
```text
FUNCTION hasMoreVowelsIterative(s)
    cleaned_s = CONVERT TO LOWERCASE(s)
    vowels = 0
    consonants = 0
    
    FOR i = 0 TO LENGTH(cleaned_s) - 1 DO
        char = cleaned_s[i]
        IF char IS ENGLISH LETTER THEN
            IF char IS VOWEL THEN
                vowels = vowels + 1
            ELSE
                consonants = consonants + 1
            END IF
        END IF
    END FOR
    
    RETURN vowels > consonants
END FUNCTION
```

### 3. โปรแกรมภาษา Java ที่สามารถทำงานได้จริง
* โค้ดโปรแกรมอยู่ในไฟล์ `VowelConsonantCounter.java` มีการแยกเมธอดชัดเจน ทำงานตามเงื่อนไขละเว้นตัวเลข ช่องว่าง เครื่องหมายพิเศษ และอักษรพิมพ์ใหญ่/เล็ก

### 4. ตัวอย่างข้อมูลนำเข้าและผลลัพธ์
* **Input:** `education` (สระ 5, พยัญชนะ 4) -> **Output:** `true`
* **Input:** `Hello World 123!` (สระ 3, พยัญชนะ 7) -> **Output:** `false` (ข้ามตัวเลข 123 ช่องว่าง และเครื่องหมาย !)
* **Input:** `Rhythm` (สระ 0, พยัญชนะ 6) -> **Output:** `false`

### 5. การวิเคราะห์ Time Complexity
* **Recursive Counting:** $\mathcal{O}(n)$ (เมื่อ $n$ คือความยาวของสตริง) เนื่องจากการเรียกซ้ำจะเลื่อน Index ไปข้างหน้าทีละ 1 ตำแหน่งจนกว่าจะครบทุกตัวอักษร
* **Iterative Counting:** $\mathcal{O}(n)$ เนื่องจากลูปทำงาน $n$ รอบเพื่อตรวจสอบตัวอักษรแต่ละตัวตั้งแต่ต้นจนจบ

### 6. การวิเคราะห์ Space Complexity
* **Recursive Counting:** $\mathcal{O}(n)$ เนื่องจากต้องเก็บสถานะการทำงานไว้ใน Call Stack ลึกลงไปตามจำนวนตัวอักษร ($n$ ชั้น)
* **Iterative Counting:** $\mathcal{O}(1)$ ใช้เพียงตัวแปรเก็บค่าตัวเลขไม่กี่ตัว (`vowels`, `consonants`, `i`) โดยไม่ขึ้นอยู่กับความยาวของสตริง

### 7. การเปรียบเทียบข้อดีและข้อจำกัดของแต่ละอัลกอริทึม
* **จำนวน Recursive Calls:** วิธีเวียนเกิดจะมีการเรียกเมธอดซ้อนกันเป็นจำนวน $n + 1$ ครั้ง ($n$ ครั้งสำหรับอักษรแต่ละตัว และอีก 1 ครั้งสำหรับ Base Case)
* **ความเสี่ยงของ StackOverflowError:** วิธี Recursive มีความเสี่ยงสูงมาก หากสตริงมีความยาวมากๆ (เช่น ข้อความระดับบทความที่มีความยาวหลายพันหรือหมื่นอักขระ) หน่วยความจำ Call Stack จะเต็มและโปรแกรมจะพังทันที ในขณะที่ Iterative ไม่มีความเสี่ยงนี้เลย

### 8. สรุปว่าอัลกอริทึมใดเหมาะสมกว่าภายใต้เงื่อนไขใด
* **ขนาดข้อมูลที่เหมาะสม:**
  * **Recursive Algorithm:** เหมาะสำหรับข้อมูลสตริงที่มีขนาดเล็กหรือสั้นๆ เท่านั้น (เช่น ไม่เกินหลักร้อยตัวอักษร)
  * **Iterative Algorithm:** เหมาะสำหรับข้อมูลทุกขนาด รวมถึงสตริงที่มีขนาดใหญ่มาก (ระดับหลักหมื่นตัวอักษรขึ้นไป)
* **สรุป:** **Iterative Counting มีความเหมาะสมมากกว่า** ในการนำไปใช้งานจริง เนื่องจากไม่เปลืองหน่วยความจำ และตัดปัญหาความเสี่ยงเรื่อง StackOverflowError ออกไปได้อย่างสิ้นเชิง 
