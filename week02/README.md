# Java tasks & Git


# [Git] Task 0 Setup your GitHub account and prepare your repository
 - Create GitHub account
 - Download and install git [git-scm](https://git-scm.com/downloads)
 - Generate SSH key for GitHub authentication [ssh-gen-paper](https://www.purdue.edu/science/scienceit/ssh-keys-windows.html)
  Example for ssh command with Windows
  ```
  ssh-keygen -t rsa -b 4096 -C "your_email@example.com"
  ```
  for mac it's located inside '/Users/YourUsername/.ssh/id_rsa' for windows 'C:\Users\YourUsername\.ssh\id_rsa'
 - Setup SSH with GitHub [documentation](https://docs.github.com/en/authentication/connecting-to-github-with-ssh) 
 - Create repository and clone it or init local repository and later on connect you remote repository

# [Git] Practice merge on local repository (together or with the cheatsheet)
1. Create folder 'git-lab-02' and init repository inside it
<details>
<summary>Steps</summary>

```sh
mkdir git-lab-02
cd git-lab-02
git init
```

</details>

2. Create initial commit creating a text file
<details>
<summary>Steps</summary>

```sh
echo "Hello, Git World" > greeting.txt
git add greeting.txt
git commit -m "Initial commit for adding the first line of greeting.txt"
```

</details>

3. Create a feature branch. You receive a task in which you need to add one more line to the greeting.txt
<details>
<summary>Steps</summary>


## Create and switch to a new branch
```sh
git checkout -b feature-branch
```
## Make changes in the feature branch
```sh
echo "Hello from the feature branch" > greeting.txt
git add greeting.txt
git commit -m "Update from feature branch to greeting.txt"
```

</details>

4. Return to master branch and alter greeting.txt file
<details>
<summary>Steps</summary>


## Switch back to the master branch
```sh
git checkout master
```
## Make conflicting changes in the master branch
```sh
echo "Hello from the master branch" > greeting.txt
git add greeting.txt
git commit -m "Update from master branch"

```

</details>

5. Merge feature-branh to master
<details>
<summary>Steps</summary>


## Attempt to merge feature-branch into master
```sh
git merge feature-branch
```

## Result
```
Auto-merging greeting.txt
CONFLICT (content): Merge conflict in greeting.txt
Automatic merge failed; fix conflicts and then commit the result.
```
</details>

6. Resolve conflicts

<details>
<summary>Steps</summary>

## Validate where are your conflicted lines
Open the conflicting file in a text editor and resolve the conflicts
For example, the file might look like this:
```
<<<<<<< HEAD
Hello from the master branch
=======
Hello from the feature branch
>>>>>>> feature-branch
```
Remove the lines and add: 'Hello from both branches'

```sh
vi greeting.txt
#alter file by entering 'i' (edit mode), delete lines and add the appropriate line. Close with :wq
```

## Execute after save
After resolving it could look like this:
Hello from both branches

## After resolving the conflict, add the file and commit the resolution
```sh
git add greeting.txt
git commit -m "Resolve merge conflict between master and feature branch"
git log --all --decorate --oneline --graph
```

</details>

7. You are taking a new feature task - feature-branch-2. Start by creating a branch
<details>
<summary>Steps</summary>


## Reset your starting point
```sh
git checkout master
```

## Create a new feature branch
```sh
git checkout -b feature-branch-2
```

## Make changes in the new feature branch
```sh
echo "Another feature update" > feature.txt
git add feature.txt
git commit -m "Add feature in feature-branch-2"
```
</details>

8. Create changes to the feature file inside master (create the file)
<details>
<summary>Steps</summary>


## Switch to master
```sh
git checkout master
```

## Add text line to feature.txt
```sh
echo "Updates from master for conflict" > feature.txt
git add feature.txt
git commit -m "Update feature.txt on master for rebase conflict"
```

```
georgiminkov@Georgis-MacBook-Pro git-lab-01 % git log --all --decorate --oneline --graph
* c5d244a (HEAD -> master) Update feature.txt on master for rebase conflict
| * 59ca35e (feature-branch-2) Add feature in feature-branch-2
|/  
*   74e0483 Resolve merge conflict between master and feature branch
|\  
| * 271e26a (feature-branch) Update from feature branch to greeting.txt
* | 38dc12b Update from master branch
|/  
* cec5ff8 Initial commit for adding the first line of greeting.txt
```
</details>

9. Rebase feature-branch-2 over master
<details>
<summary>Steps</summary>


## Switch to feature-branch-2 to start the rebase
```sh
git checkout feature-branch-2
```

## Begin the rebase
```sh
git rebase master
```
```
georgiminkov@Georgis-MacBook-Pro git-lab-01 % git rebase master
CONFLICT (add/add): Merge conflict in feature.txt
Auto-merging feature.txt
error: could not apply 59ca35e... Add feature in feature-branch-2
Resolve all conflicts manually, mark them as resolved with
"git add/rm <conflicted_files>", then run "git rebase --continue".
You can instead skip this commit: run "git rebase --skip".
To abort and get back to the state before "git rebase", run "git rebase --abort".
Could not apply 59ca35e... Add feature in feature-branch-2
```
</details>

10. Resolve conflicts
<details>
<summary>Steps</summary>


## Switch to feature-branch-2 to start the rebase
## Resolve the conflict in feature.txt manually
```
Git marked the file like this:
<<<<<<< HEAD
Another feature update
=======
Updates from master for conflict
>>>>>>> master
```

## Combined updates from both master and feature-branch-2

## After resolving the conflict, add the file to mark it as resolved
```sh
git add feature.txt
```

## Continue the rebase after resolving the conflict

```sh
git rebase --continue
```

## Add commit message and exit with :wq

## Final tree
```
* b9cc4f8 (HEAD -> feature-branch-2) Add feature in feature-branch-2
* c5d244a (master) Update feature.txt on master for rebase conflict
*   74e0483 Resolve merge conflict between master and feature branch
|\  
| * 271e26a (feature-branch) Update from feature branch to greeting.txt
* | 38dc12b Update from master branch
|/  
* cec5ff8 Initial commit for adding the first line of greeting.txt
```
</details>


# Task 2 Setup GUI
- IntelliJ
- TortoiseGIT [download](https://tortoisegit.org/download/)
- SourceTree [download](https://www.sourcetreeapp.com)
- GitKraken [download](https://www.gitkraken.com)

# Task 3
Finsh tasks from [lab01](https://github.com/dreamix-fmi-course-2024/web-development-with-java-lab/blob/main/lab01/tasks.md#additional-tasks) and push them to your repository