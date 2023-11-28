Arkadaşlar,
Branchinizi eclipse command linedan şu şekil açın:

git branch <branchname> // Create the branch.
git checkout <branchname> // Switch to the branch.
git commit -m "created branch" // Commit the branch.
git push --set-upstream origin <branchname> // Create the branch in github.

Kendi branchinize pushlayın kodlarınızı merge conflicten kafayı yemeyelim.
Teşekkürler
# KuAlchemists

# Take the latest main <br/>
Go to main: git checkout main <br/>
Pull main: git pull origin main<br/>
Go to beyza: git checkout beyza<br/>
Then: git merge main <br/>

# I did some change in Beyza branch. I would like to merge it with main;
*I am in Beyza branch:* <br/>
Git add . <br/>
Git commit -m “message” <br/>
Git push <br/>
*I go to main branch* <br/>
Git checkout main <br/>
Merge local beyza to local main: Git merge beyza <br/>
Push local main to remote main: git push -u origin main <br/>
