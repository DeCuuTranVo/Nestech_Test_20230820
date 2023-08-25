/*
Nhập một mảng số nguyên. Liệt kê các phần tử xuất hiện trong mảng đúng 1 lần.
ví dụ:
[1, 2, 3, 1, 2, 5] => 3, 5 chỉ xuất hiện 1 lần
 */

let n = Number(prompt("Input Number of Elements:"));
//alert(n);
let arr = [];

for (let i = 0; i < n; i++) {
    arr.push(Number(prompt(`Input Elements #${i}:`)))
}

let freqDict = {};

for (let i = 0; i < n; i++) {
    if (!(arr[i] in freqDict)) {
        freqDict[arr[i]] = 1;
    } else {
        freqDict[arr[i]] = freqDict[arr[i]] + 1;
    }
}

for (let key in freqDict) {
    if (freqDict[key] > 1) {
        delete freqDict[key];
    }
}

alert("Distinct values: " + Object.keys(freqDict));
//str = JSON.stringify(freqDict, null, 4);
