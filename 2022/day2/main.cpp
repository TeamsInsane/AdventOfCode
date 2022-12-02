#include <iostream>
#include <fstream>
#include <map>

using namespace std;

map<char, int> scorePerPlay = {
        {'A', 1},
        {'B', 2},
        {'C', 3}
};
map<char, int> scorePerEnd = {
        {'X', 0},
        {'Y', 3},
        {'Z', 6}
};

int getScore(char o, char m);

int main() {
    int finalScore = 0;

    ifstream data("../input.txt");
    string value;
    while(getline(data, value)){
        finalScore += getScore(value[0], value[2]);
    }

    cout << finalScore;

    return 0;
}

int getScore(char o, char m){
    int score = scorePerEnd[m];
    if (o == 'A'){
        if (m == 'X') m = 'C';
        else if (m == 'Y') m = o;
        else m = 'B';
    } else if (o == 'B'){
        if (m == 'X') m = 'A';
        else if (m == 'Y') m = o;
        else m = 'C';
    } else {
        if (m == 'X') m = 'B';
        else if (m == 'Y') m = o;
        else m = 'A';
    }

    score += scorePerPlay[m];

    return score;
}


