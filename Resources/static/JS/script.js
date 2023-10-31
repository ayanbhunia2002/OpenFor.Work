const radioButtons = document.querySelectorAll('input[name="profile-type"]');
const profileLinksContainer = document.getElementById("profile-links");

radioButtons.forEach(radio => {
    radio.addEventListener("change", function () {
        const selectedProfile = this.value;

        // Clear any previous profile links
        profileLinksContainer.innerHTML = "";

        if (selectedProfile === "development") {
            createProfileLink("GitHub");
            createProfileLink("Stack Overflow");
            createProfileLink("LeetCode");
        } else if (selectedProfile === "design") {
            createProfileLink("Behance");
            createProfileLink("Dribbble");
            createProfileLink("Figma");
        } else if (selectedProfile === "content") {
            createProfileLink("Medium");
            createProfileLink("Personal Blog");
        } else if (selectedProfile === "marketing") {
            createProfileLink("Facebook");
            createProfileLink("Instagram");
            createProfileLink("YouTube");
        }
    });
});

function createProfileLink(profileName, defaultValue = "") {
    const linkLabel = document.createElement("label");
    linkLabel.textContent = `${profileName} Profile Link: `;
    const linkInput = document.createElement("input");
    linkInput.type = "url";
    linkInput.name = `profileLinks['${profileName}']`; // Set the correct name format
    linkInput.placeholder = `Enter your ${profileName} profile link`;
    if (defaultValue) {
        linkInput.value = defaultValue;
    }

    // Create a line break for spacing
    const lineBreak = document.createElement("br");

    // Append elements to the profileLinks div
    profileLinksContainer.appendChild(linkLabel);
    profileLinksContainer.appendChild(linkInput);
    profileLinksContainer.appendChild(lineBreak);
}

function showContent(type) {
      var contentDisplay = document.getElementById('contentDisplay');
      var content = '';
  
      if (type === 'work') {
        content = `<div>
                        <img th:src="@{/img/github-logo.png}" height="20" style="margin-bottom:5px "> <b style="font-size: 20px;"> GitHub </b>
                    </div>    
                    <div>
                        <b>Total Repositories <span th:text="${user.public_repos}"></span></b>
                        <b> Total Followers <span th:text="${user.followers}"></span> </b> 
                            
                    </div>
                    <div>
    <b> <a th:href="@{'https://github.com/' + ${username} + '?tab=repositories'}" style="text-decoration: none; color: #000;">
        View All Repo
    </a></b>
</div>
</div>`;
      } else if (type === 'resume') {
        content = `<p class="text-center mt-5 text-danger" style="font-weight: bold;">RESUME NOT YET UPLOADED</p>`
      } else if (type === 'project') {
        content = `<p class="text-center mt-5 text-danger" style="font-weight: bold;">PROJECTS NOT YET UPLOADED</p>`
      }
  
      contentDisplay.innerHTML = content;
    }
