#include <iostream>
#include <fstream>
#include <algorithm>

using namespace std;

void bothStars(int n);

int main(){

    bothStars(4);
    bothStars(14);

    return 0;
}

void bothStars(int n){
    ifstream data("../input.txt");
    string input;
    char temp;
    int count = 0;

    string tempString;
    do{
        do{
            data >> temp;
            input += temp;
            count++;
        } while (input.size() < n);

        tempString = input;
        std::sort(tempString.begin(), tempString.end());
        tempString.erase(std::unique(tempString.begin(), tempString.end()), tempString.end());
        input.erase(input.begin());

    } while (tempString.size() != input.size()+1);

    cout << count << endl;
    data.close();
}