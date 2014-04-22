/*
 *ID:   Cowboy
 *TASK:
 *Judge:
 */
#include <bits/stdc++.h>
#include <time.h>
#define INF 2000000000
#define PI 2*acos(0.0)
using namespace std;

#define PB(t) push_back(t)
#define ALL(t) t.begin(),t.end()
#define MP(x, y) make_pair((x), (y))
#define ROW_SIZE 3      // ROW_SIZE es una matriz de 3 x 3
#define PUZZLE (ROW_SIZE*ROW_SIZE)

const int movX[] = {0,-1,0,1};
const int movY[] = {-1,0,1,0};

struct Board{
    int tiles[PUZZLE];
    long long id;
    int x, y;

    Board(){
        x = -1;
        y = -1;
        id = -1;
    }

    long long getID(){
        long long state = 0;
        for(int i = 0; i < ROW_SIZE; i++) {
            for (int j = 0; j < ROW_SIZE; j++) {
                state <<= 4;
                state += tiles[ i * ROW_SIZE + j ];
                if( tiles[ i * ROW_SIZE + j ]==PUZZLE-1 ){
                    x = i;
                    y = j;
                }
            }
        }
        id = state;
        return state;
    }
    //De matriz a numero
    long long toID( int p[3][3] ){
        long long state = 0;
        for(int i = 0; i < ROW_SIZE; i++) {
            for (int j = 0; j < ROW_SIZE; j++) {
                state <<= 4;
                state += p[i][j];
                tiles[ i * ROW_SIZE + j ] = p[i][j];

                if( p[i][j]==PUZZLE-1 ){
                    x = i;
                    y = j;
                }

            }
        }
        return state;
    }

    //De Numero a Matriz
    void toBoard( long long _id ) {
        for(int i = 3-1; i >= 0; i--) {
            for (int j = 3-1; j >= 0; j--) {
                tiles[i * ROW_SIZE + j] = _id%16;
                _id /= 16;
                if( tiles[i * ROW_SIZE + j]==PUZZLE-1 ){
                    x = i;
                    y = j;
                }
            }
        }
    }

    Board( long long _id ){
        id = _id;
        toBoard( _id );
    }

    Board( int _tiles[ROW_SIZE][ROW_SIZE] ){
        //tiles = _tiles;
        id = toID( _tiles );
    }

    int hamming() {// return number of blocks out of place
        //Not supported yet
        return 0;
    }
    int manhattan(){// return sum of Manhattan distances between blocks and goal
        //Not supported yet
        return 0;
    }

    bool operator==( const Board& b1 ){// does this board equals to b1
        return (this->id)==b1.id;
    }

    vector<Board> neighbors() { // return an Iterable of all neighboring board positions
        vector<Board> aux;
        for(int k = 0; k < 4; k ++){
            int nx, ny;

            nx = this->x + movX[k];//nueva posicion a donde voy a moverme
            ny = this->y + movY[k];

            if(nx >= 0 and nx <= 2 and ny >= 0 and ny <= 2 ){
                Board cAux( id );
//                cout<<"-"<<id<<endl;
//                cout<<"*"<<cAux.id<<cAux.toString()<<endl;
                swap( cAux.tiles[ x * ROW_SIZE + y ] , cAux.tiles[ nx * ROW_SIZE + ny ] );
                cAux.getID();
//                cout<<"**"<<cAux.id<<cAux.toString()<<endl;
                aux.PB( cAux );
            }
        }
        return aux;
    }

    string toString(){      // return a string representation of th
        string print = " [ ";
        for(int i = 0; i < 3; i++){
            print.append("< ");
            for (int j = 0; j < 3; j++)
            {
                print.append(" ");
                print += ( tiles[ i * ROW_SIZE + j ]+'0'+1 );
            }
            print.append(" > ");
        }
        print.append( " ]" );

        return print;
    }
};

struct Solver{
    stack <Board> sol;
    Board objetive;
    int cnt;

    bool IDFS( Board initial, int lim ){
        int  cont = 1;
        stack <Board> p;
        stack <int> pint;
        map<unsigned long long, int> vis;

        p.push( initial );
        pint.push( cont );

        while( !p.empty() ){
            initial = p.top(); p.pop();
            cont = pint.top(); pint.pop();
            //cout<< initial.toString() <<" "<<cont<<" "<<vis[initial.id]<<endl;
            //if( cont%5==0 )system("pause");
            if( initial == objetive ){
//                cout<< initial.toString()<<endl;
                //sol.push(initial);
                cnt = cont;
                return true;
            }

            if( (vis[initial.id]>0 && vis[initial.id]<cont) || cont>lim){
                continue;
            }
            //sol.push( initial );
            vis[initial.id] = cont;

            vector<Board> ves = initial.neighbors();
            for( int i=0; i < ves.size(); i++){
                //cout<<"VES:"<< ves[i].toString() <<endl;
                p.push( ves[i] );
                pint.push( cont+1 );
            }

        }
        return false;
    }

    Solver( Board initial ){// find a solution to the initial board
        int depth = 10;
        cnt = 0;
        sol = stack<Board>();
        objetive = Board(305419896);
        while( !IDFS( initial, depth) ){
            sol = stack<Board>();
            depth += 10;
        }
    }

    bool isSolvable(){// is the initial board solvable?
        //Not Supported yet
        return true;
    }

    int moves() {    // return min number of moves to solve initial board; -1 if no solution
        return cnt;
    }

    vector<Board> solution(){// return an Iterable of board positions in solution
        vector<Board>res;
        stack<Board>aux = sol;
        while( !aux.empty() ){
            res.PB( aux.top() );
            aux.pop();
        }
        return res;
    }
};

int main( ){
    clock_t t_ini, t_fin;
    double secs;
    freopen("input.txt","r",stdin);
    freopen("outIDFS6.txt","w",stdout);
    for(int t, k = 0; cin>>t ; k++){
        int aux1[3][3];
        aux1[0][0]=t-1;
        cin>>t;
        aux1[0][1]=t-1;
        cin>>t;
        aux1[0][2]=t-1;
        for(int i = 1; i < 3; i++) {
            for( int j = 0; j < 3; j++) {
               cin>>aux1[i][j];
               aux1[i][j]--;
            }
        }
        t_ini = clock();
        Board board( aux1 );
        vector<Board>ves = board.neighbors();
        Solver solver( board );
//        ves = solver.solution();
//        for( int i=ves.size()-1; i>=0; i-- ){
//            cout<<ves[i].toString()<<endl;
//        }
        t_fin = clock();
        secs = (double)(t_fin - t_ini) / CLOCKS_PER_SEC;
        printf("%d\t %d\t %.16g\n"
               , k+1, solver.moves(), secs * 1000.0);
    }
return 0;
}
