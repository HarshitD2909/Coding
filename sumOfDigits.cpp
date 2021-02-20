
/**
Tourist is a 3rd-grade elementary student and quite well in mathematics. Once, Tourist's teacher asked him to calculate the sum of numbers 1 through n.
Tourist quickly answered, and his teacher made him another challenge. He asked Tourist to calculate the sum of the digits of numbers 1 through n.
Tourist did finally find the solution. Now it is your turn, can you find a solution?
Input
Two space-separated integers 0 <= a <= b <= 10^9.

Program terminates if a and b are -1.
Output
The sum of the digits of numbers a through b.
Sample Input
1 10
100 777
-1 -1
Sample Output
46
8655
**/
#include <bits/stdc++.h>

using namespace std;
int noOfdigits(int n)
{   int l=0;
    while(n!=0)
    {
        n=n/10;
        l++;
    }
    return l;
}
int solve(int n,int nod)
{    if(nod==0)
        return 0;
     int po=ceil(pow(10,nod));

     int fd=(n*10)/po;

     int temp=0;
     int csum=(po*45*(nod-1))/100;
     int sum=0;
    while(temp<fd)
    {
       sum+=(temp*po)/10+csum;
       temp++;
    }
    sum+=fd*(n%(po/10)+1);
    return sum+solve(n%(po/10),nod-1);
}
int main()
{
    int a,b;
    cin>>a>>b;
    while(!(a==-1&&b==-1))
    {int noda=0;
    if(a!=0)
    noda=noOfdigits(a-1);
    int nodb=noOfdigits(b);
    if(a==0)
        cout<<solve(b,nodb)<<endl;
    else
    cout<<solve(b,nodb)-solve(a-1,noda)<<endl;
    cin>>a>>b;
    }
}
