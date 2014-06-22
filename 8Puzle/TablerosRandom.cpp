#include <bits/stdc++.h>

using namespace std;
#define TAM 30
//const int TAM = 10;

int v[3][3];

int rdtsc(){
    __asm__ __volatile__("rdtsc");
}

int ini(){
    for(int k = 0; k < 3;k++){
        for(int j = 1; j <= 3; j++){
            v[k][j - 1] = 3*k + j;
        }
    }
}

    void print(){
        for(int k = 0; k < 3; k ++){
            for(int j = 0; j < 3; j++){
                cout<<v[k][j]<<" ";
            }
            cout<<endl;
        }
        cout<<endl;
    }

int tablerosRand(){
    int posi = 2;
    int posj = 2;
    int a = 200;

    for(int k = 0; k < a; k ++){

        int n = rand()%12+1;
        if(n%4 == 1 and posi - 1 >= 0){
            int aux = v[posi - 1][posj];
            v[posi - 1][posj] = v[posi][posj];
            v[posi][posj] = aux;
            posi = posi - 1;
        }
        if(n%4 == 2 and posj + 1 < 3){
            int aux = v[posi][posj + 1];
            v[posi][posj + 1] = v[posi][posj];
            v[posi][posj] = aux;
            posj = posj + 1;
        }
        if(n%4 == 3 and posi + 1 < 3){
            int aux = v[posi + 1][posj];
            v[posi + 1][posj] = v[posi][posj];
            v[posi][posj] = aux;
            posi = posi + 1;
        }
        if(n%4 == 0 and posj - 1 >= 0){
            int aux = v[posi][posj - 1];
            v[posi][posj - 1] = v[posi][posj];
            v[posi][posj] = aux;
            posj = posj - 1;
        }

    }
}
int main(){

    freopen("input.txt","w",stdout);
    srand(rdtsc());

    for(int k = 0; k < TAM; k++){
        ini();
        tablerosRand();
        print();
    }

}
