# ข้อ 2 การตรวจสอบ Palindrome

### 1. คำอธิบายแนวคิดของอัลกอริทึม
* **Algorithm 1 (Reverse and Compare):** ทำการสร้างสตริงใหม่ที่เรียงลำดับตัวอักษรย้อนกลับจากสตริงต้นฉบับ จากนั้นนำสตริงทั้งสองมาเปรียบเทียบกัน หากเหมือนกันทุกประการแสดงว่าเป็น Palindrome
* **Algorithm 2 (Recursive Two-Pointer):** ใช้ตัวชี้ (Pointer) สองตัว คือ `left` ชี้ที่ตัวอักษรแรก และ `right` ชี้ที่ตัวอักษรสุดท้าย นำมาเปรียบเทียบกัน หากตรงกันให้ขยับตัวชี้เข้าหากัน (`left+1`, `right-1`) แล้วเรียกซ้ำ (Recursive) เพื่อตรวจสอบคู่ถัดไปจนกว่าตัวชี้จะชนกันตรงกลาง

*(หมายเหตุ: ทั้ง 2 อัลกอริทึมมีการจัดการสตริงก่อนประมวลผล โดยการตัดช่องว่าง เครื่องหมายวรรคตอน และแปลงเป็นตัวพิมพ์เล็กทั้งหมด เพื่อให้ตรงตามเงื่อนไขเพิ่มเติมของโจทย์)*

### 2. Pseudocode
**Algorithm 1: Reverse and Compare**
```text
FUNCTION isPalindromeByReverse(s)
    cleaned_s = REMOVE NON-ALPHANUMERIC AND CONVERT TO LOWERCASE(s)
    reversed_s = REVERSE(cleaned_s)
    IF cleaned_s == reversed_s THEN
        RETURN true
    ELSE
        RETURN false
    END IF
END FUNCTION
```

**Algorithm 2: Recursive Two-Pointer**
```text
FUNCTION isPalindromeRecursive(s, left, right)
    IF left >= right THEN
        RETURN true
    END IF
    
    IF s[left] != s[right] THEN
        RETURN false
    END IF
    
    RETURN isPalindromeRecursive(s, left + 1, right - 1)
END FUNCTION
```

### 3. งานวิเคราะห์และเปรียบเทียบ (Analysis)

| กรณี / หัวข้อการวิเคราะห์ | Algorithm 1 (Reverse and Compare) | Algorithm 2 (Recursive Two-Pointer) |
| :--- | :--- | :--- |
| **กรณีที่สตริงเป็น Palindrome** | ต้องทำการกลับสตริงทั้งหมด 1 รอบ และเปรียบเทียบทีละตัวอักษรอีก 1 รอบ | ต้องทำการเปรียบเทียบตัวอักษรและเรียกซ้ำจนถึงตรงกลางของสตริง (ทำงาน $\frac{n}{2}$ รอบ) |
| **กรณีที่ตัวอักษรคู่แรกไม่ตรงกัน** | **ยังคงต้องทำงานเต็มรูปแบบ** (กลับสตริงทั้งหมดและนำมาเปรียบเทียบ) จึงจะรู้ว่าไม่ตรงกัน | **ทำงานเพียง 1 รอบแล้วหยุดทันที** เนื่องจากตรวจพบความขัดแย้งตั้งแต่คู่แรก |
| **ความสามารถในการหยุดก่อน (Early Exit)** | ❌ **ไม่มี** ต้องประมวลผลทั้งสตริงเสมอ | ✅ **มี** หากพบตัวอักษรไม่ตรงกัน จะคืนค่า `false` และหยุดการทำงานทันที |
| **Best-case Time Complexity** | $\mathcal{O}(n)$ (เพราะต้องสลับด้านสตริงทั้งหมดเสมอ แม้ตัวแรกจะไม่ตรงกัน) | $\mathcal{O}(1)$ (กรณีตัวอักษรคู่แรกไม่ตรงกัน โปรแกรมจะหยุดทำงานทันทีโดยไม่ต้องวนลูปต่อ) |
| **Worst-case Time Complexity** | $\mathcal{O}(n)$ | $\mathcal{O}(n)$ (กรณีที่เป็น Palindrome ต้องตรวจสอบไปจนถึงตรงกลาง) |
| **Space Complexity** | $\mathcal{O}(n)$ (ต้องใช้พื้นที่สร้างสตริงใหม่สำหรับการกลับด้าน) | $\mathcal{O}(n)$ (ใช้พื้นที่ใน Call Stack ซ้อนกันลึกสูงสุด $\frac{n}{2}$ ชั้น) |

### 4. สรุป
**Recursive Two-Pointer มีประสิทธิภาพโดยรวมดีกว่า** เนื่องจากมีคุณสมบัติ Early Exit ทำให้ในกรณีที่สตริงไม่ได้เป็น Palindrome อัลกอริทึมจะสามารถจบการทำงานได้อย่างรวดเร็วมาก (Time Complexity เป็น $\mathcal{O}(1)$) ในขณะที่วิธี Reverse and Compare จะต้องเสียเวลาและความจำไปกับการสร้างสตริงใหม่ทั้งชุดเสมอไม่ว่าข้อมูลนำเข้าจะเป็นอย่างไร
