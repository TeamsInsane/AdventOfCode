#include <iostream>
#include <fstream>
#include <vector>

using namespace std;

void firstStar(vector<int> values, int number);
void fillValues(vector<int>& values);
void secondStar(vector<int> values, int number);

int main(){

    vector<int> values;
    fillValues(values);

    return 0;
}

void fillValues(vector<int>& values){
    ifstream data("../input.txt");
    int number;
    while(data >> number){
        secondStar(values, number);
        values.push_back(number);
    }
}

void firstStar(vector<int> values, int number){
    for (int i = 0; i < values.size(); ++i) {
        if (values.at(i)+number == 2020){
            cout << values.at(i)*number;
            exit(0);
        }
    }
}

void secondStar(vector<int> values, int number){
    for (int i = 0; i < values.size(); ++i) {
        for (int j = 0; j < values.size(); ++j) {
            if (i == j) continue;

            if (values.at(i)+values.at(j)+number == 2020){
                cout << values.at(i)*number*values.at(j);
                exit(0);
            }
        }
    }
}