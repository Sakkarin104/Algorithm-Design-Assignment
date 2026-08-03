# ข้อ 1 การกลับลำดับสตริง (String Reversal)

### 1. คำอธิบายแนวคิดของอัลกอริทึม
* **Recursive Algorithm:** นำตัวอักษรตัวสุดท้ายของสตริง มาต่อกับผลลัพธ์ของการเรียกซ้ำ (ส่งสตริงที่ตัดตัวสุดท้ายออกไปแล้วเข้าไปใหม่) ทำซ้ำจนกว่าสตริงจะว่าง
* **Iterative Algorithm:** ใช้ลูปถอยหลัง เริ่มอ่านจากดัชนีสุดท้าย (Index) ย้อนกลับไปดัชนีแรกสุด แล้วนำตัวอักษรมาต่อกัน

### 2. Pseudocode
**Recursive:**
```text
FUNCTION reverseRecursive(s)
    IF s is empty THEN RETURN s
    last_char = GET LAST CHARACTER OF s
    remaining = REMOVE LAST CHARACTER FROM s
    RETURN last_char + reverseRecursive(remaining)
```
**Iterative:**
```text
FUNCTION reverseIterative(s)
    IF s is empty THEN RETURN s
    result = ""
    FOR i = LENGTH(s) - 1 DOWN TO 0
        result = result + s[i]
    RETURN result
```

### 3. การอธิบาย Base Case และ Recursive Case
* **Base Case:** `if (s == null || s.isEmpty())` หยุดการเวียนเกิดและคืนค่าเดิมกลับไปเมื่อสตริงว่างเปล่า
* **Recursive Case:** `return lastChar + reverseRecursive(remainingString);` ตัดตัวอักษรสุดท้ายออก แล้วเรียกตัวเองซ้ำด้วยสตริงที่สั้นลง

### 4. งานวิเคราะห์ (Analysis)
* **จำนวนครั้งที่ประมวลผล:** ทั้ง 2 วิธีประมวลผล $n$ ครั้ง ($n$ คือความยาวของสตริง)
* **Time Complexity:** 
  * **Recursive:** $\mathcal{O}(n^2)$ เพราะการใช้ `+` ต่อสตริงใน Java ใช้เวลา $\mathcal{O}(n)$ ต่อรอบ
  * **Iterative:** $\mathcal{O}(n)$ เพราะลูปทำงาน $n$ รอบ และการใช้ `StringBuilder` ใช้เวลา $\mathcal{O}(1)$ ต่อรอบ
* **Space Complexity:** 
  * **Recursive:** $\mathcal{O}(n)$ จากการใช้ Call Stack ซ้อนกัน $n$ ชั้น (แต่อาจพุ่งสูงถ้ารวมหน่วยความจำการสร้าง String ใหม่)
  * **Iterative:** $\mathcal{O}(n)$ ใช้พื้นที่เก็บ `StringBuilder` แค่ 1 ชุด

### 5. ผลกระทบของเครื่องหมาย `+` และความต่างของ `String` กับ `StringBuilder`
* **String:** เป็น Immutable (แก้ไขไม่ได้) การต่อด้วย `+` จะสร้าง Object ใหม่เสมอ เปลืองหน่วยความจำและทำงานช้าลง
* **StringBuilder:** เป็น Mutable (แก้ไขได้) สามารถนำข้อมูลไปต่อท้ายพื้นที่เดิมได้ทันที จึงทำงานกับลูปได้เร็วและมีประสิทธิภาพสูงกว่า

### 6. การเปรียบเทียบและสรุปผล
* **ข้อดี-ข้อจำกัด:** Recursive โค้ดกระชับแต่กินหน่วยความจำ เสี่ยงเกิด `StackOverflowError` (พิสูจน์แล้วที่ 10,000 ตัวอักษร) ส่วน Iterative เขียนยาวกว่าเล็กน้อยแต่ทำงานได้รวดเร็วและเสถียร
* **สรุป:** **Iterative Algorithm เหมาะสมกว่า** ภายใต้เงื่อนไขการทำงานจริง เพราะสามารถรองรับข้อมูลสตริงขนาดใหญ่ (หลักหมื่นตัวอักษรขึ้นไป) ได้อย่างมีประสิทธิภาพโดยที่โปรแกรมไม่พัง
