#include <iostream>
#include <vector>
#include <bits/stdc++.h>

using namespace::std;

int main() {

    ifstream data("../input.txt");

    vector <int> inputs;
    string number;
    int temp = 0;

    while (getline(data, number)){
        if (number.empty()){
            inputs.push_back(temp);
            temp = 0;
        } else
            temp+= stoi(number);
    }

    sort(inputs.begin(), inputs.end());

    int index = inputs.size() - 1;

    cout << inputs[index] + inputs[index-1] + inputs[index-2];

    return 0;
}
