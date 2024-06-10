# a-greedy-approach-problem
A problem with a football club recruiting and hiring players at the lowest cost.


Assume that you are the owner of a football club. Your club has enough trainers to improve and promote ‘p’ players to the main squad from the youth team. Some other clubs ask to rent players from your club and make several demands for renting players from your club. The number of demands can be different from year to year. You should design a renting plan for the next ‘n’ years. Consider ‘i’ is the index of each year (i=1,…,n) and yi is the number of players will be requested to rent for ith year. If your club needs to promote more than ‘p’ players for a year, you can hire some coaches, paying ‘c’ TL costs per player for that year. However, if your club keeps any unrented player in that year, you should pay a ‘salary’ for a such player.

An example to understand the problem:

Your current trainers can promote p=5 players in a year, for a four years plan (n=4), y[ ] = { 8, 4, 7, 4 } and Salary(1) = 5, Salary(2)= 7, Salary(3)=10, Salary(4)=12, Salary(5)=13, etc. and c=10 TL.

For the first year, your trainers can promote 5 players, however, the demand is 8. You can hire coaches with the cost of c*(8-5)=10*3= 30 TL.

For the second year, the demand is 4, will you promote 5 players and keep 1 player in the squad for the next years for not paying coach costs? Or will you just promote just 4 players?

![image](https://github.com/0asa0/a-greedy-approach-problem/assets/134441532/89870b8b-b557-4657-a149-926384c4f30a)

