Sử dụng thuật toán APRIORI khởi tạo bằng JAVA

===============================
* Author: Đặng Hoàng Nguyên, Nguyễn Minh Trí
* THUẬT TOÁN APRIORI tìm tập hợp phần tử thường xuyên và quy tắc khai phá luật kết hợp dựa vào minimum confident và minimum support

Thuật toán APRIORI được chia thành 2 phần 
===
1. Tìm tập phổ biến của dataset
   * Xác định được Minimum Support và Minimum Confidence.
   * Tìm những phần tử có độ xuất hiện (phổ biến) lớn hơn Minimum Support gom lại thành 1 tập hợp
   * Từ tập hợp đó, tìm những tập con của chúng (khác rỗng) và quét trên CSDL và lăp lại bước số 2
2. Khởi tạo luật kết hợp tính liên kết của chúng có bền vững hay không.

CÔNG THỨC TÍNH
===
1. Support (A) = (số lần 'A xuất hiện trong database) / (tổng số giao dịch)
2. Confidence (A->B) = Support (A U B) / Support (A)

DATASET 01
===
Mỗi dòng là một giao dịch
```
TID: 	Itemset: 
T400	2,5
T200	2,3,5
T300	1,2,3,5
T100	1,3,4
```


OUTPUT
===
```
|--------------------SUMMARRY ITEM DETAIL-------------------|
+-------------+------------------+------------+-------------+
|No Of items  |No of transaction |Min support |Min confident|
+-------------+------------------+------------+-------------+
5              4                  2.000000     0.670000
|--------------------Distinct item in list------------------|
         4         2         1         5         3
+-------------+------------------+------------+-------------+
|TRANSACTION ID                                      ITEMSET|
|T400                                                    2,5|
|T200                                                  2,3,5|
|T300                                                1,2,3,5|
|T100                                                  1,3,4|

+-------------+------------------+------------+-------------+
```
```
+-------------+------------------+------------+-------------+
                       GROWTH NUMBER 1 
|ITEM                                                SUPPORT|
|5                                                       3.0|
|3                                                       3.0|
|1                                                       2.0|
|2                                                       3.0|
+-------------+------------------+------------+-------------+
```
```
+-------------+------------------+------------+-------------+
                        GROWTH NUMBER 2
|ITEM                                                SUPPORT|
|2,5                                                     3.0|
|5                                                       3.0|
|3,5                                                     2.0|
|3                                                       3.0|
|1,3                                                     2.0|
|1                                                       2.0|
|2,3                                                     2.0|
|2                                                       3.0|
+-------------+------------------+------------+-------------+
```
```
+-------------+------------------+------------+-------------+
                        GROWTH NUMBER 3
|ITEM                                                SUPPORT|
|2,5                                                     3.0|
|2,3,5                                                   2.0|
|5                                                       3.0|
|3,5                                                     2.0|
|3                                                       3.0|
|1,3                                                     2.0|
|1                                                       2.0|
|2,3                                                     2.0|
|2                                                       3.0|
+-------------+------------------+------------+-------------+
```
```
Luật kết hợp cho các phần tử xuất hiện thường xuyên

+-------------+------------------+------------+-------------+
|---------------------GROWTH ASSOCIATION--------------------|
+-------------+------------------+------------+-------------+
|Assocation   |No of transaction |Status                    |
|2 --> 5       min_conf = 1.0     Strong Association        |
|5 --> 2       min_conf = 1.0     Strong Association        |
|23 --> 5      min_conf = 1.0     Strong Association        |
|25 --> 3      min_conf = 0.667                             |
|2 --> 3       min_conf = 0.667                             |
|3 --> 2       min_conf = 0.667                             |
|35 --> 2      min_conf = 1.0     Strong Association        |
|3 --> 1       min_conf = 0.667                             |
|5 --> 23      min_conf = 0.667                             |
|5 --> 3       min_conf = 0.667                             |
|3 --> 5       min_conf = 0.667                             |
|3 --> 25      min_conf = 0.667                             |
|1 --> 3       min_conf = 1.0     Strong Association        |
|2 --> 35      min_conf = 0.667                             |
+-------------+------------------+------------+-------------+
