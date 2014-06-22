#include <bits/stdc++.h>

using namespace std;

int movX[] = {0,1,0,-1};
int movY[] = {1,0,-1,0};
vector <bool> visited;
int fact[10] = {1,1,2,6,24,120,720,5040,40320,362880};
int res;

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
//de matriz a numero
    int data(){
        int pos;
        int d = 0;
        vector<int> digits;// = {1,2,3,4,5,6,7,8,9};
        for(int i=1;i<=9; i++)
            digits.push_back(i);
        for ( int k = 8; k >= 0 ; k-- ){
            pos = find(digits.begin(), digits.end(), v[k / 3][k % 3]) - digits.begin();//en que posicion esta el digito que esta procesando
            digits.erase(digits.begin() + pos);//lo borro
            d += pos * fact[k];
        }
        return d;
    }

    //pasar de numero a matriz
    cuadro(int d){//constructor
        vector<int> digits ;//= {1,2,3,4,5,6,7,8,9};
        for(int i=1;i<=9; i++)
            digits.push_back(i);
        int pos;
        id = d;
        for ( int k = 8; k >= 0 ; k-- ){
            pos = d / fact[k];
            v[k / 3][k % 3] = digits[pos];
            //saber donde esta el 0
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
    q.push(cont);
    visited.clear();
    visited.resize(fact[9]);
    visited[cua] = true;

    while(!q.empty()){
        cuadro c(q.front());q.pop();
        //c.print();
        cont = q.front();
        q.pop();
        //c.print();
        if(c.isValid()){
            //c.print();
            res = cont;
           return true;
        }

        for(int k = 0; k < 4; k ++){
            int nx, ny;
            cuadro cAux = c;

            nx = c.x + movX[k];//nueva posicion a donde voy a moverme
            ny = c.y + movY[k];

            if(nx >= 0 and nx <= 2 and ny >= 0 and ny <= 2 ){
                cAux.v[c.x][c.y] = cAux.v[nx][ny];  // el nuevo cuadro que voy a guardar, osea ES EL HIJO Y LO ESTOY CREANDO
                cAux.v[nx][ny] = 9;

                if(!(visited[cAux.data()])){
                    visited[cAux.data()] = true;
                    q.push(cAux.data());//esto mete el entero
                    q.push(cont+1);
                }
            }
        }
    }
    return false;
}

int main(){
    //cuadro c(fact[9]-1);
    //freopen("input1.txt","w",stdout);

    freopen("input.txt","r",stdin);
    freopen("outBFS2.txt","w",stdout);
    clock_t t_ini, t_fin;
    double secs;
    cuadro c(fact[9]-1);

    int cont = 0;
    int one = 0,two = 0,tree = 0;
    while(cin>>one>>two>>tree){

        c.v[0][0] = one;
        c.v[0][1] = two;
        c.v[0][2] = tree;

        for (int k = 1; k < 3; k++) {
            for (int j = 0; j < 3; j++) {
                cin>>c.v[k][j];
            }
        }
        //cout<<"-> Caso: " <<cont++<<endl;
        t_ini = clock();
        bfs(c.data());

        t_fin = clock();
        secs = (double)(t_fin - t_ini) / CLOCKS_PER_SEC;
        printf("%d:\t %d\t %.16g\n"
               , cont++, res, secs * 1000.0);
    }
    return 0;
}
