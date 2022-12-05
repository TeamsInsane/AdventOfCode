#include <iostream>
#include <vector>
#include <bits/stdc++.h>

using namespace std;

void fillList(vector<vector<char>> &crates);
void firstStar(vector<vector<char>> crates);
void printAnswer(vector<vector<char>> crates);
void secondStar(vector<vector<char>> crates);

int main(){
    vector<vector<char>> crates{{},{},{},{},{},{},{},{},{}};

    fillList(crates);
    firstStar(crates);
    secondStar(crates);

    return 0;
}

void fillList(vector<vector<char>> &crates){
    ifstream data("../input.txt");
    string tempString;
    char tempChar;
    while(getline(data, tempString) && tempString[1] != '1'){
        for(int i = 0; !tempString.empty(); i++){
            tempChar = tempString[1];
            if (tempString.size() > 3) tempString = tempString.substr(4);
            else tempString = "";

            if (tempChar != ' ') crates.at(i).push_back(tempChar);
        }
    }
    data.close();
    for(auto & crate : crates) reverse(crate.begin(), crate.end());
}

void firstStar(vector<vector<char>> crates){
    ifstream data("../input.txt");
    string tempString;
    stringstream tempStringStream;
    while (getline(data, tempString)){
        if (tempString[0] != 'm') continue;
        tempStringStream = (stringstream) tempString;
        vector<int> movement;
        while (!tempStringStream.eof()){
            tempStringStream >> tempString;
            int tempInt;
            if(stringstream(tempString) >> tempInt) movement.push_back(tempInt); //movement[0] = count, movement[1] = from, movement[2] = to
        }
        for(int i = 0; i < movement[0]; i++){
            crates[movement[2]-1].push_back(crates[movement[1]-1][crates[movement[1]-1].size()-1]);
            crates[movement[1]-1].pop_back();
        }
    }
    printAnswer(crates);
}

void secondStar(vector<vector<char>> crates){
    ifstream data("../input.txt");
    string tempString;
    stringstream tempStringStream;
    while (getline(data, tempString)){
        if (tempString[0] != 'm') continue;
        tempStringStream = (stringstream) tempString;
        vector<int> movement;
        while (!tempStringStream.eof()){
            tempStringStream >> tempString;
            int tempInt;
            if(stringstream(tempString) >> tempInt) movement.push_back(tempInt); //movement[0] = count, movement[1] = from, movement[2] = to
        }
        for(int i = 0; i < movement[0]; i++)
            crates[movement[2]-1].push_back(crates[movement[1]-1][crates[movement[1]-1].size()-movement[0]+i]);
        for(int i = 0; i < movement[0]; i++)
            crates[movement[1]-1].pop_back();
    }
    printAnswer(crates);
}

void printAnswer(vector<vector<char>> crates){
    for (auto & crate : crates)
        cout << crate[crate.size()-1];
    cout << endl;
}