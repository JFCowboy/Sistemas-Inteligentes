#include <bits/stdc++.h>

using namespace std;

int movX[] = {0,1,0,-1};
int movY[] = {1,0,-1,0};
vector <bool> visited;
int fact[10] = {1,1,2,6,24,120,720,5040,40320,362880};

struct cuadro{

    int v[3][3] ;
    int x, y;
    int id;

    bool isValid(){
        return id == 362879;
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
    int data(){
        int pos;
        int d=0;
        vector<int> digits = {1,2,3,4,5,6,7,8,9};
        for ( int k = 8; k >= 0 ; k-- ){
            pos = find(digits.begin(), digits.end(), v[k / 3][k % 3]) - digits.begin();

            digits.erase(digits.begin() + pos);
            d += pos * fact[k];
        }

        return d;
    }

    cuadro(int d){//constructor
        vector<int> digits = {1,2,3,4,5,6,7,8,9};
        int pos;
        id = d;
        for ( int k = 8; k >= 0 ; k-- ){
            pos = d / fact[k];
            v[k / 3][k % 3] = digits[pos];
            if ( v[k / 3][k % 3] == 9 ) {
                x = k / 3;//guardar donde esta el 0
                y = k % 3;//guardar donde esta el 0
            }
            digits.erase(digits.begin() + pos);
            d %= fact[k];
        }
    }
};

bool bfs(int cua){
    int  cont = 0;
    queue <int> q;
    q.push(cua);
    visited.clear();
    visited.resize(fact[9]);
    visited[cua] = true;

    while(!q.empty()){
        cuadro c(q.front());

        if(cont++%8215 == 0){
            cerr<<":)"<<endl;
            c.print();
        }
        q.pop();
        //DESCOMENTAR
       // if(c.isValid()){
         //   return true;
        //}

        for(int k = 0; k < 4; k ++){
            int nx, ny;
            cuadro cAux = c;

            nx = c.x + movX[k];//nueva posicion a donde voy a moverme
            ny = c.y + movY[k];

            if(nx >= 0 and nx <= 2 and ny >= 0 and ny <= 2){
                cAux.v[c.x][c.y] = cAux.v[nx][ny];  // el nuevo cuadro que voy a guardar, osea ES EL HIJO Y LO ESTOY CREANDO
                cAux.v[nx][ny] = 9;

                visited[cAux.data()] = true;
                q.push(cAux.data());//esto mete el entero
            }
        }
    }
    return false;
}

int main(){
    cuadro c(fact[9]-1);
    freopen("input.txt","w",stdout);
    bfs(c.data());

    return 0;
}
