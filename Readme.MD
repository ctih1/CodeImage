# CodeImage

## Usage
``java -jar CodeImage.jar <username> <repo> <branch> <.filetype> <token> <(optional) type>``

## Example
``java -jar CodeImage.jar ctih1 CodeImage master .java (PAT) minHeight``

## Arguementes
- Username
  - Your GitHub username.
- Repo
  - Your repo **name**
- Filetype
  - The filetype (ex: .js)
- Token
  - GitHub personal access token. https://github.com/settings/tokens?type=beta
- *Type*
  - maxHeight:
    - Prioritize the entire code.
    
    <img src="https://i.ibb.co/HHN4FV0/max-Height.png">
  - minHeight:
    - Prioritize the 'look'
    
    <img src="https://i.ibb.co/yqzGqxb/min-Height.png">
  
