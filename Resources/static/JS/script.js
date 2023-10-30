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
