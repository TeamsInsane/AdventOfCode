#include <iostream>
#include <fstream>
#include <algorithm>

using namespace std;

void readValues();
int firstStar(string repeatTimes, string character, string value);
int secondStar(string repeatTimes, string character, string value);

int main(){

    readValues();

    return 0;
}

void readValues(){
    int score = 0;
    ifstream data("../input.txt");

    string repeatTimes;
    string character;
    string value;
    while (data >> repeatTimes){
        data >> character;
        data >> value;

        score += secondStar(repeatTimes, character, value);
    }

    cout << score;
}


int firstStar(string repeatTimes, string character, string value){
    int count = std::count(value.begin(), value.end(), character[0]);

    string max, min;

    for (int i = 0; i < repeatTimes.size(); ++i) {
        if (repeatTimes[i] == '-') {
            max = repeatTimes.substr((i + 1), repeatTimes.size() - 1);
            break;
        }
        else
            min += repeatTimes[i];
    }

    if (count >= stoi(min) && count <= stoi(max)) return 1;
    return 0;
}

int secondStar(string repeatTimes, string character, string value){

    string no, yes;

    for (int i = 0; i < repeatTimes.size(); ++i) {
        if (repeatTimes[i] == '-') {
            no = repeatTimes.substr((i + 1), repeatTimes.size() - 1);
            break;
        }
        else
            yes += repeatTimes[i];
    }

    if ((value[stoi(yes)-1] == character[0] && value[stoi(no)-1] != character[0]) || (value[stoi(yes)-1] != character[0] && value[stoi(no)-1] == character[0])) return 1;
    return 0;
}