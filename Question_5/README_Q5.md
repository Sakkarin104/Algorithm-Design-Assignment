# ข้อ 5 การแบ่งอาร์เรย์ตามค่า k

### 1. คำอธิบายแนวคิดของอัลกอริทึมแต่ละวิธี
* **อัลกอริทึมที่ 1 (Recursive Partition):** ใช้ตัวชี้ `left` จากด้านซ้าย และ `right` จากด้านขวา หาก `a[left]` มีค่าน้อยกว่าหรือเท่ากับ $k$ ถือว่าอยู่ถูกฝั่งแล้ว ให้เลื่อน `left` ไปทางขวา หาก `a[right]` มีค่ามากกว่า $k$ ถือว่าอยู่ถูกฝั่งแล้ว ให้เลื่อน `right` ไปทางซ้าย หากเจอตัวที่อยู่ผิดฝั่งทั้งคู่ ให้ทำการสลับค่า (Swap) แล้วเรียกซ้ำ (Recursion)
  * **Base Case (กรณีฐาน):** `if (left >= right)` หยุดการทำงานเมื่อตัวชี้ชนกันหรือไขว้กัน
  * **Recursive Case (กรณีเวียนเกิด):** เรียกตัวเองซ้ำโดยขยับตัวชี้ `left` และ/หรือ `right` ตามเงื่อนไขของข้อมูล
* **อัลกอริทึมที่ 2 (Iterative Partition):** ใช้แนวคิดตัวชี้คู่ (Two-Pointer) แบบเดียวกับวิธีแรกทุกประการ แต่เปลี่ยนจากการเรียกซ้ำมาใช้ลูป `while` ในการเลื่อนตัวชี้เข้าหากันและสลับค่าแทน 
* **อัลกอริทึมที่ 3 (Sorting-Based Algorithm):** อาศัยการนำอาร์เรย์ทั้งชุดไปเรียงลำดับ (Sort) จากน้อยไปมาก เมื่อเรียงเสร็จแล้ว สมาชิกที่มีค่าน้อยกว่าหรือเท่ากับ $k$ จะไปกองอยู่ด้านหน้าโดยอัตโนมัติ และสมาชิกที่มากกว่า $k$ จะอยู่ด้านหลัง

### 2. Pseudocode หรือผังขั้นตอนการทำงาน
**Algorithm 1: Recursive Partition**
```text
FUNCTION partitionRecursive(a, k, left, right)
    IF left >= right THEN RETURN END IF
    
    IF a[left] <= k THEN
        partitionRecursive(a, k, left + 1, right)
    ELSE IF a[right] > k THEN
        partitionRecursive(a, k, left, right - 1)
    ELSE
        SWAP(a[left], a[right])
        partitionRecursive(a, k, left + 1, right - 1)
    END IF
END FUNCTION
```

**Algorithm 2: Iterative Partition**
```text
FUNCTION partitionIterative(a, k)
    left = 0
    right = LENGTH(a) - 1
    
    WHILE left < right DO
        WHILE left < right AND a[left] <= k DO left = left + 1 END WHILE
        WHILE left < right AND a[right] > k DO right = right - 1 END WHILE
        
        IF left < right THEN
            SWAP(a[left], a[right])
            left = left + 1
            right = right - 1
        END IF
    END WHILE
END FUNCTION
```

**Algorithm 3: Sorting-Based Algorithm**
```text
FUNCTION partitionBySorting(a, k)
    SORT(a) IN ASCENDING ORDER
    // หลังจาก Sort ข้อมูลจะถูกแบ่งกลุ่มตามค่า k โดยอัตโนมัติ
END FUNCTION
```

### 3. โปรแกรมภาษา Java ที่สามารถทำงานได้จริง
* โค้ดโปรแกรมฉบับสมบูรณ์ถูกจัดเก็บไว้ในไฟล์ `ArrayPartition.java` มีการทำงานครบทั้ง 3 อัลกอริทึมตามที่โจทย์กำหนด

### 4. ตัวอย่างข้อมูลนำเข้าและผลลัพธ์
* **Input:** `A = [12, 4, 7, 15, 3, 10, 8]`, `k = 8`
* **Output (Recursive/Iterative):** `[8, 4, 7, 3, 15, 10, 12]` *(หมายเหตุ: ลำดับภายในกลุ่มอาจแตกต่างกันไปตามการทำงานของอัลกอริทึม แต่จะได้กลุ่ม $\le 8$ อยู่หน้า และ $> 8$ อยู่หลังเสมอ)*
* **Output (Sorting-Based):** `[3, 4, 7, 8, 10, 12, 15]`

### 5. การวิเคราะห์ Time Complexity
* **Recursive Partition:** $\mathcal{O}(n)$ เพราะตัวชี้ `left` และ `right` เลื่อนเข้าหากันเพื่อตรวจสอบข้อมูลตัวละ 1 ครั้งจนจบ
* **Iterative Partition:** $\mathcal{O}(n)$ ทำงานด้วยลูปแบบวิ่งเข้าหากัน 1 รอบเช่นเดียวกัน
* **Sorting-Based:** $\mathcal{O}(n \log n)$ เป็นเวลามาตรฐานสำหรับการเรียงลำดับข้อมูลด้วยอัลกอริทึมที่มีประสิทธิภาพ (เช่น Quick Sort หรือ Merge Sort ที่ภาษา Java ใช้เป็นค่าเริ่มต้น)

### 6. การวิเคราะห์ Space Complexity
* **Recursive Partition:** $\mathcal{O}(n)$ ในกรณีแย่ที่สุด (หรือ $\mathcal{O}(\log n)$ แบบเฉลี่ย) จากการจองพื้นที่ใน Call Stack สำหรับการเรียกซ้ำ
* **Iterative Partition:** $\mathcal{O}(1)$ เพราะใช้เพียงตัวแปรชี้ตำแหน่งไม่กี่ตัว
* **Sorting-Based:** $\mathcal{O}(\log n)$ ถึง $\mathcal{O}(n)$ ขึ้นอยู่กับอัลกอริทึมการเรียงลำดับเบื้องหลังของภาษา Java 

### 7. การเปรียบเทียบและวิเคราะห์ประเด็นเพิ่มเติม
* **เหตุผลที่การเรียงลำดับอาจทำให้โปรแกรมช้ากว่าที่จำเป็น:** การเรียงลำดับ (Sorting) ทำงานมากเกินความจำเป็นของโจทย์ เพราะโจทย์ต้องการแค่แบ่ง "กลุ่ม" (ซ้าย-ขวา) ไม่ได้ต้องการให้สมาชิกในกลุ่มต้องเรียงลำดับกันเอง การเรียงลำดับใช้เวลา $\mathcal{O}(n \log n)$ ซึ่งช้ากว่าการแบ่งกลุ่มด้วยลูปที่ใช้เวลาเพียง $\mathcal{O}(n)$
* **ความสัมพันธ์ของปัญหานี้กับขั้นตอน Partition ใน Quick Sort:** อัลกอริทึมการแบ่งกลุ่มนี้ คือหัวใจหลัก (Core Step) ของ **Quick Sort** อย่างแท้จริง (รู้จักกันในชื่อ *Hoare's Partition Scheme*) โดยเปลี่ยนค่า $k$ ในโจทย์ ให้ทำหน้าที่เสมือน **Pivot** ใน Quick Sort นั่นเอง
* **การระบุว่าวิธีใดทำงานแบบ In-place ได้:** **อัลกอริทึมที่ 1 (Recursive)** และ **อัลกอริทึมที่ 2 (Iterative)** สามารถทำงานแบบ In-place ได้อย่างแท้จริง เพราะเป็นการสลับค่าในอาร์เรย์เดิมโดยไม่ต้องสร้างอาร์เรย์ใหม่ขึ้นมาช่วย (โดย Iterative จะเป็น In-place ที่สมบูรณ์แบบที่สุดเพราะ Space เป็น $\mathcal{O}(1)$)

### 8. สรุปว่าอัลกอริทึมใดเหมาะสมกว่าภายใต้เงื่อนไขใด
**Iterative Partition เป็นอัลกอริทึมที่เหมาะสมที่สุด** สำหรับปัญหานี้ เพราะสามารถทำงานได้สำเร็จในเวลาเพียง $\mathcal{O}(n)$ และใช้หน่วยความจำ $\mathcal{O}(1)$ (In-place) โดยไม่เสี่ยงต่อการเกิด StackOverflow แตกต่างจาก Sorting-Based ที่ทำงานช้ากว่าโดยไม่จำเป็น
