#include <iostream>
#include <fstream>
#include <map>

using namespace std;

map<int, int> score = {
        {1, 0},
        {3, 0},
        {5, 0},
        {7, 0},
        {12, 0},
};

void firstStar();
void secondStar();

int main(){

    firstStar();
    secondStar();

    return 0;
}

void firstStar(){
    ifstream data("../input.txt");

    string value;
    int st = 0, count = 0;
    while (getline(data, value)){
        while (value.size() < st) value+=value;

        if (value[st] == '#') count++;
        st+=3;
    }

    cout << count << endl;
}

void secondStar(){
    ifstream data("../input.txt");

    string value;
    int st = 0;
    unsigned long long count = 1;

    while (getline(data, value)){
        for(auto it = score.begin(); it != --score.end(); it++){
            while (value.size() <= st*it->first) value+=value;

            if (value[it->first*st] == '#') it->second++;
        }

        if (st % 2 == 0 && st != 0 && value[st / 2] == '#') score[12]++;

        st++;
    }

    for(auto & it : score) count *= it.second;

    cout << count;
}