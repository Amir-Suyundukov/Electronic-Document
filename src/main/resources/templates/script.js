async function loadOrganizations() {
    const response = await fetch('/api/organizations');
    const organizations = await response.json();
    const contentDiv = document.getElementById('content');
    contentDiv.innerHTML = `
        <h2>Organizations</h2>
        <button onclick="createOrganization()">Add Organization</button>
        <ul>
            ${organizations.map(org => `
                <li>
                    ${org.name}
                    <button onclick="editOrganization(${org.id})">Edit</button>
                    <button onclick="deleteOrganization(${org.id})">Delete</button>
                </li>
            `).join('')}
        </ul>
    `;
}

function createOrganization() {
    const name = prompt("Enter organization name:");
    // Add additional fields as needed
    if (name) {
        fetch('/api/organizations', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ name })
        }).then(loadOrganizations);
    }
}

function editOrganization(id) {
    const name = prompt("Enter new organization name:");
    // Add additional fields as needed
    if (name) {
        fetch(`/api/organizations/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ name })
        }).then(loadOrganizations);
    }
}

function deleteOrganization(id) {
    if (confirm("Are you sure you want to delete this organization?")) {
        fetch(`/api/organizations/${id}`, {
            method: 'DELETE'
        }).then(loadOrganizations);
    }
}

// Similar functions for loading, creating, editing, and deleting Departments, Employees, and Tasks
