/*========= Program to find the Permutation of a given String =====================	*/
/*========= This program is written by Mohd Umar (Master-Tech271) ===========================================*/
/*========= Example -: HISTORY = 5040 possible permutation (HISTORY is a 7 word string then we can calculate the factorial of 7 is 5040) =============== 	*/
// C header files include in our program
#include<stdio.h> // for standard input output
#include<string.h> // for string
#include<conio.h> //for console input output 
//prototypes of functions that are used in a program
void permutation(char *, int, int);// generate all possible combination of a given string by a user
void swap(char*, char*); // used for swaping between indexes of a char array
//main function where the execution started
int main() {
 char *c;
 clrscr();
 printf("Enter a string : ");
 gets(c);//take a string char by char until new line
 permutation(c, 0, strlen(c)-1); //call the permutation funtion
 getch();
 return 0;
}

//definitions of functions

//header of function
void permutation(char *s, int i, int n) { 
 //function body
 int j;
 static int count;
 if(i==n) {
  count++;
  printf("%d %s \n", count, s);
 }
 else {
  for(j=i; j<=n; j++) {
   swap((s+i), (s+j));// call of swap function
   permutation(s, i+1, n);
   swap((s+i), (s+j));//again call of swap function but different values
  }
 }
}//end of permutation

//header of function
void swap(char *x, char *y) {
//function body
 char c;
 c = *x;
 *x = *y;
 *y = c;
}//end of swap

/*================================== END PROGRAM ==================================================*/