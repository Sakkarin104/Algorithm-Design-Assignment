# ข้อ 4 การจัดกลุ่มจำนวนคู่และจำนวนคี่

### 1. คำอธิบายแนวคิดของอัลกอริทึมแต่ละวิธี
* **อัลกอริทึมที่ 1 (Recursive Two-Pointer):** ใช้ตัวชี้ `left` และ `right` เพื่อค้นหาข้อมูลจากสองฝั่งของอาร์เรย์แบบเวียนเกิด (Recursion) หาก `left` ชี้ที่จำนวนคู่ ให้ขยับ `left` ไปทางขวา หาก `right` ชี้ที่จำนวนคี่ ให้ขยับ `right` ไปทางซ้าย แต่ถ้า `left` เจอจำนวนคี่และ `right` เจอจำนวนคู่ ให้ทำการสลับค่า (Swap) ทั้งสองตำแหน่งแล้วเรียกซ้ำเพื่อทำขั้นตอนต่อไป
  * **Base Case (กรณีฐาน):** `if (left >= right)` หยุดการทำงานเมื่อตัวชี้ทั้งสองวิ่งมาชนกันหรือไขว้กัน
  * **Recursive Case (กรณีเวียนเกิด):** เรียกใช้ตัวเองซ้ำโดยปรับเปลี่ยนตำแหน่ง `left` และ/หรือ `right` ตามเงื่อนไขที่พบในรอบปัจจุบัน
* **อัลกอริทึมที่ 2 (Iterative Two-Pointer):** ใช้หลักการทำงานแบบเดียวกับวิธีแรกทุกประการ เพียงแต่เปลี่ยนจากการเวียนเกิดมาใช้ลูป `while (left < right)` แทน ทำให้ไม่ต้องเรียกเมธอดซ้อนกัน
* **อัลกอริทึมที่ 3 (Extra Array):** ใช้หลักการสร้างอาร์เรย์ใหม่ขนาดเท่าเดิมขึ้นมา แล้วใช้วงลูปอ่านอาร์เรย์ต้นฉบับ 2 รอบ รอบแรกนำเฉพาะจำนวนคู่ไปใส่เรียงในอาร์เรย์ใหม่ก่อน จากนั้นรอบที่สองจึงนำเฉพาะจำนวนคี่ไปใส่ต่อท้ายจนครบ

### 2. Pseudocode หรือผังขั้นตอนการทำงาน
**Algorithm 1: Recursive Two-Pointer**
```text
FUNCTION rearrangeRecursive(a, left, right)
    IF left >= right THEN RETURN END IF
    
    IF a[left] is EVEN THEN
        rearrangeRecursive(a, left + 1, right)
    ELSE IF a[right] is ODD THEN
        rearrangeRecursive(a, left, right - 1)
    ELSE
        SWAP(a[left], a[right])
        rearrangeRecursive(a, left + 1, right - 1)
    END IF
END FUNCTION
```

**Algorithm 2: Iterative Two-Pointer**
```text
FUNCTION rearrangeTwoPointer(a)
    left = 0
    right = LENGTH(a) - 1
    
    WHILE left < right DO
        WHILE left < right AND a[left] is EVEN DO left = left + 1 END WHILE
        WHILE left < right AND a[right] is ODD DO right = right - 1 END WHILE
        
        IF left < right THEN
            SWAP(a[left], a[right])
            left = left + 1
            right = right - 1
        END IF
    END WHILE
END FUNCTION
```

**Algorithm 3: Extra Array**
```text
FUNCTION rearrangeExtraArray(a)
    CREATE result_array OF SIZE LENGTH(a)
    index = 0
    
    FOR EACH num IN a DO
        IF num is EVEN THEN
            result_array[index] = num
            index = index + 1
        END IF
    END FOR
    
    FOR EACH num IN a DO
        IF num is ODD THEN
            result_array[index] = num
            index = index + 1
        END IF
    END FOR
    
    RETURN result_array
END FUNCTION
```

### 3. โปรแกรมภาษา Java ที่สามารถทำงานได้จริง
* โค้ดโปรแกรมฉบับสมบูรณ์ถูกจัดเก็บไว้ในไฟล์ `EvenOddRearranger.java` ภายในโฟลเดอร์นี้ ซึ่งสามารถรันเพื่อตรวจสอบการคงลำดับ (Stability) ได้ตามที่โจทย์กำหนด

### 4. ตัวอย่างข้อมูลนำเข้าและผลลัพธ์
* **Input (ตามโจทย์):** `[7, 2, 9, 4, 1, 6, 3, 8]`
  * **Output (Two-Pointer):** `[8, 2, 6, 4, 1, 9, 3, 7]` *(ลำดับอาจสลับกันไปมา)*
* **Input (การทดสอบความ Stable):** `[5, 2, 7, 4, 9, 6]`
  * **Stable Output (Extra Array เท่านั้น):** `[2, 4, 6, 5, 7, 9]`

### 5. การวิเคราะห์ Time Complexity
* **Recursive Two-Pointer:** $\mathcal{O}(n)$ เนื่องจากตัวชี้ทำงานวิ่งเข้าหากันจนจบครบทุกตัวอักษร 1 รอบ
* **Iterative Two-Pointer:** $\mathcal{O}(n)$ ทำงานแบบลูปวิ่งเข้าหากัน 1 รอบเช่นเดียวกัน
* **Extra Array:** $\mathcal{O}(n)$ เนื่องจากต้องวนลูปอ่านอาร์เรย์จำนวน 2 รอบ (รอบแรกเก็บคู่ รอบสองเก็บคี่) ค่า Big-O จึงเป็น $2n$ ตัดคงที่เหลือ $\mathcal{O}(n)$

### 6. การวิเคราะห์ Space Complexity
* **Recursive Two-Pointer:** $\mathcal{O}(n)$ (หรือ $\mathcal{O}(\log n)$ ในบางกรณีของข้อมูล) จากการใช้ Call Stack ในการเรียกซ้ำ
* **Iterative Two-Pointer:** $\mathcal{O}(1)$ เพราะใช้เพียงตัวแปรชี้ตำแหน่ง `left`, `right` ไม่กี่ตัว ไม่เปลืองหน่วยความจำ
* **Extra Array:** $\mathcal{O}(n)$ เนื่องจากการสร้างอาร์เรย์ใหม่ขึ้นมาทั้งชุดซึ่งมีขนาดเท่ากับอาร์เรย์เดิม

### 7. การเปรียบเทียบข้อดีและข้อจำกัดของแต่ละอัลกอริทึม
การเปรียบเทียบนี้จะอิงจากเงื่อนไขการวิเคราะห์ 4 หัวข้อที่กำหนดไว้ในโจทย์ ได้แก่:
1. **การเปลี่ยนแปลงอาร์เรย์เดิม (In-place Modification):** แบบ Recursive และ Iterative เป็นการแก้ไขข้อมูลภายในอาร์เรย์เดิม (In-place) ในขณะที่แบบ Extra Array จะคงอาร์เรย์เดิมไว้แล้วคืนค่าอาร์เรย์ชุดใหม่ (Out-of-place)
2. **จำนวนครั้งของการสลับข้อมูล (Swaps):** แบบ Two-Pointer ทั้งคู่จะเกิดการสลับข้อมูลตามจำนวนเลขคี่ที่หลงไปอยู่ฝั่งซ้าย (มากสุดไม่เกิน $rac{n}{2}$ ครั้ง) ส่วนแบบ Extra Array ไม่มีการสลับข้อมูลเลย (0 swaps) ใช้เพียงการคัดลอกค่าลงช่องใหม่
3. **ความเป็น Stable Algorithm:** (หมายถึงความสามารถในการรักษาก่อน-หลังของข้อมูลเดิม) 
   * **Two-Pointer (ทั้งคู่): ไม่เป็น Stable Algorithm ❌** เพราะการดึงตัวเลขจากท้ายอาร์เรย์มาสลับมาไว้ด้านหน้า จะทำลายลำดับเดิมของตัวเลขทันที
   * **Extra Array: เป็น Stable Algorithm ✅** เพราะลูปรอบแรกดึงจำนวนคู่มาเรียงตามลำดับเดิมเป๊ะๆ และลูปรอบสองก็ดึงจำนวนคี่มาเรียงตามลำดับเดิมเป๊ะๆ เช่นกัน

### 8. สรุปว่าอัลกอริทึมใดเหมาะสมกว่าภายใต้เงื่อนไขใด
* **Iterative Two-Pointer เหมาะสมที่สุด หากเน้นประสิทธิภาพหน่วยความจำ:** เพราะกินพื้นที่เพียง $\mathcal{O}(1)$ และแก้ไขอาร์เรย์เดิมได้ทันทีโดยไม่ต้องสร้างตัวแปรเปลือง (แต่ต้องยอมรับว่าข้อมูลจะเสียลำดับ)
* **Extra Array เหมาะสมที่สุด หากมีเงื่อนไขบังคับว่า "ต้องเป็น Stable Algorithm":** ในระบบที่ความถูกต้องของลำดับการจัดเรียงสำคัญกว่าความประหยัดหน่วยความจำ วิธีนี้จะเป็นทางเลือกเดียวที่ตอบโจทย์ได้สมบูรณ์แบบ
