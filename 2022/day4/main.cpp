#include <iostream>
#include <fstream>
#include <vector>
#include <sstream>

using namespace std;

void bothStars();

int main(){

    bothStars();

    return 0;
}

void bothStars(){
    int count1 = 0, count2 = 0;
    ifstream data("../input.txt");
    stringstream streamValue;
    string tempValue;
    vector<int> finalValues;
    while(getline(data, tempValue)){
        finalValues.clear();
        streamValue = (stringstream) tempValue;
        while(getline(streamValue, tempValue, ',')) {
            stringstream assignment = (stringstream) tempValue;
            while (getline(assignment, tempValue, '-')) finalValues.push_back(stoi(tempValue));
        }

        if ((finalValues[0] <= finalValues[2] && finalValues[1] >= finalValues[3]) || (finalValues[0] >= finalValues[2] && finalValues[1] <= finalValues[3])) count1++;

        if (finalValues[0] <= finalValues[3] && finalValues[1] >= finalValues[2]) count2++;
    }

    cout << count1 << endl;
    cout << count2 << endl;
}