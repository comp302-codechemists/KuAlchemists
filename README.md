Arkadaşlar,
Branchinizi eclipse command linedan şu şekil açın:

git branch <branchname> // Create the branch.
git checkout <branchname> // Switch to the branch.
git commit -m "created branch" // Commit the branch.
git push --set-upstream origin <branchname> // Create the branch in github.

Kendi branchinize pushlayın kodlarınızı merge conflicten kafayı yemeyelim.
Teşekkürler
# KuAlchemists

# Take the latest main
Go to main: git checkout main
Pull main: git pull origin main
Go to beyza: git checkout beyza
Then: git merge main


# I did some change in Beyza branch. I would like to merge it with main
*I am in Beyza branch:*
Git add .
Git commit -m “message”
Git push
*I go to main branch*
Git checkout main
Merge local beyza to local main: Git merge beyza
Push local main to remote main: git push -u origin main
