#include <iostream>
#include <fstream>
#include <vector>

using namespace std;

void firstStar();
int checkCycles(int count, int X);
void secondStar();
void checkCTR(vector<string>& CTR, string sprite, int &count);

int main(){

    firstStar();
    secondStar();

    return 0;
}

void firstStar(){
    ifstream data("../input.txt");
    string input;
    int count = 0, X = 1, sum = 0;
    while (getline(data, input)){
        count++;
        sum += checkCycles(count, X);

        if (input.substr(0, 4) == "addx") {
            count++;
            sum += checkCycles(count, X);
            X += stoi(input.substr(4));
        }

    }
    cout << sum << endl;
    data.close();
}

int checkCycles(int count, int X){
    for(int i = -20, j = 1; i <= 220; i += j*40)
        if (count == i)
            return X * i;

    return 0;
}

void secondStar(){
    string sprite = "###.....................................";
    vector<string> CTR(6);

    ifstream data("../input.txt");
    string input;
    int count = 0, X = 1;
    while (getline(data, input)){
        checkCTR(CTR, sprite, count);

        if (input.substr(0, 4) == "addx") {
            checkCTR(CTR, sprite, count);

            X += stoi(input.substr(4));
            sprite = "........................................";
            for(int i = -1; i < 2; i++) sprite[X+i] = '#';
        }
    }
    for(auto i : CTR)
        cout << i << endl;
}

void checkCTR(vector<string>& CTR, string sprite, int &count){
    if (sprite[CTR.at(count).size()] == '#')
        CTR[count].push_back('#');
    else
        CTR[count].push_back('.');
    if (CTR[count].size() == 40) count++;
}