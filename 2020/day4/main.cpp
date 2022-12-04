#include <iostream>
#include <algorithm>
#include <fstream>
#include <vector>

using namespace std;

vector<string> expectedFields = {"byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"};
void firstStar();
int checkValue(string value);

int main(){

    firstStar();

    return 0;
}

void firstStar(){
    ifstream data("../input.txt");
    int count = 0;

    while(data.peek() != EOF){
        string value, temp;
        while (getline(data, temp)) {
            if (temp.empty()) break;
            value += temp + " ";
        }
        int tempCount = 0;
        for (int i = 0; i < expectedFields.size(); ++i) {
            if (value.find(expectedFields.at(i)) != string::npos)
                tempCount++;
        }
        if (tempCount == expectedFields.size()) count += checkValue(value);
    }


    cout << count << endl;
}

int checkValue(string value){
    string tempInt;
    string tempValue;
    do{
        tempValue = value.substr(0, value.find(' '));
        if (value.size() > 3) value = value.substr(value.find(' ') + 1);

        tempInt = tempValue.substr(4, tempValue.size());

        if (tempValue.substr(0, 3) == "byr") {
            if (stoi(tempInt) < 1920 || stoi(tempInt) > 2002) return 0;
        } else if (tempValue.substr(0, 3) == "iyr") {
            if (stoi(tempInt) < 2010 || stoi(tempInt) > 2020) return 0;
        } else if (tempValue.substr(0, 3) == "eyr") {
            if (stoi(tempInt) < 2020 || stoi(tempInt) > 2030) return 0;
        } else if (tempValue.substr(0, 3) == "ecl"){
            if (tempInt != "amb" && tempInt != "blu" && tempInt != "brn" && tempInt != "gry" && tempInt != "grn" && tempInt != "hzl" && tempInt != "oth") return 0;
        } else if (tempValue.substr(0, 3) == "pid"){
            if (tempInt.size() != 9) return 0;
        } else if (tempValue.substr(0, 3) == "hcl"){
            if (tempInt[0] != '#' && tempInt.size() != 7) return 0;
        } else if (tempValue.substr(0, 3) == "hgt"){
            if (tempInt.substr(tempInt.size()-2, 2) == "cm") {
                tempInt = tempInt.substr(0, 3);
                if (stoi(tempInt) < 150 || stoi(tempInt) > 193) return 0;
            } else if (tempInt.substr(tempInt.size()-2, 2) == "in") {
                tempInt = tempInt.substr(0, 2);
                if (stoi(tempInt) < 59 || stoi(tempInt) > 76) return 0;
            } else return 0;
        }
    } while (!value.empty() && value[0] != ' ');

    return 1;
}