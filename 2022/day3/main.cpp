#include <fstream>
#include <vector>
#include <string>
#include <iostream>

using namespace std;

vector<char> items;

char findChar1(string firstCompartment, string secondCompartment);
void firstStar();
void secondStar();
char findChar2(vector<string> &values);

int main(){

    firstStar();
    secondStar();

    return 0;
}

void firstStar(){
    ifstream data("../input.txt");
    string value;
    while(getline(data, value)){
        string firstCompartment = value.substr(0, value.size()/2);
        string secondCompartment = value.substr(value.size()/2, value.size());

        items.push_back(findChar1(firstCompartment, secondCompartment));
    }

    int sum = 0;

    for(int i = 0; i < items.size(); i++)
        if (items.at(i) <= 'Z' && items.at(i) >= 'A') sum += (int) items.at(i) - 38;
        else sum += (int) items.at(i) - 96;

    cout << sum << endl;
}

char findChar1(string firstCompartment, string secondCompartment){
    for(char i = 'a'; i <= 'z'; i++)
        if (firstCompartment.find(i) != string::npos && secondCompartment.find(i) != string::npos) return i;
        else if (firstCompartment.find(toupper(i)) != string::npos && secondCompartment.find(toupper(i)) != string::npos) return toupper(i);

    exit(1);
}

void secondStar(){
    items.clear();
    ifstream data("../input.txt");
    string temp;
    vector<string> values;
    while (true){
        if (data.peek() == EOF) break;
        for(int i = 0; i < 3; i++){
            getline(data, temp);
            values.push_back(temp);
        }

        items.push_back(findChar2(values));
        values.clear();
    }

    int sum = 0;

    for(int i = 0; i < items.size(); i++)
        if (items.at(i) <= 'Z' && items.at(i) >= 'A') sum += (int) items.at(i) - 38;
        else sum += (int) items.at(i) - 96;

    cout << sum;
}

char findChar2(vector<string> &values){
    for(char i = 'a'; i <= 'z'; i++)
        if (values[2].find(i) != string::npos && values[1].find(i) != string::npos && values[0].find(i) != string::npos) return i;
        else if (values[0].find(toupper(i)) != string::npos && values[2].find(toupper(i)) != string::npos && values[1].find(toupper(i)) != string::npos) return toupper(i);
    exit(1);
}