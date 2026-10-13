
async function loadComplaints() {

    try {

        const response = await fetch(
            "http://localhost:8081/api/complaints"
        );

        if (!response.ok) {
            throw new Error("Failed to fetch complaints");
        }

        const complaints = await response.json();

        const tableBody = document.getElementById("complaintsTableBody");

        tableBody.innerHTML = "";

        complaints.forEach(function(complaint,index) {

            const row = document.createElement("tr");

            row.innerHTML = `
                <td>${index+1}</td>
                <td>${complaint.name}</td>
                <td>${complaint.email}</td>
                <td>${complaint.category}</td>
                <td>${complaint.description}</td>
                <td>${complaint.status}</td>
                <td>${complaint.createdAt ?? ""}</td>
                <td>
                    <select id="status-${complaint.id}">
                        <option value="Pending"
                            ${complaint.status === "Pending" ? "selected" : ""}>
                            Pending
                        </option>

                        <option value="In Progress"
                            ${complaint.status === "In Progress" ? "selected" : ""}>
                            In Progress
                        </option>

                        <option value="Solved"
                            ${complaint.status === "Solved" ? "selected" : ""}>
                            Solved
                        </option>
                    </select>

                    <button onclick="updateStatus(${complaint.id})">
                        Update
                    </button>
                    
                    <button onclick="deleteComplaint(${complaint.id})">
                        Delete
                    </button>

                </td>
            `;

            tableBody.appendChild(row);

        });

    } catch (error) {

        console.error("Error loading complaints:", error);
        alert("Unable to load complaints.");

    }

}

async function updateStatus(id) {

    const selectedStatus =
        document.getElementById("status-" + id).value;

    try {

        // Get the existing complaint
        const getResponse = await fetch(
            "http://localhost:8081/api/complaints/" + id
        );

        if (!getResponse.ok) {
            throw new Error("Unable to find complaint");
        }

        const complaint = await getResponse.json();

        // Change only its status
        complaint.status = selectedStatus;

        // Send the updated complaint to Spring Boot
        const response = await fetch(
            "http://localhost:8081/api/complaints/" + id,
            {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(complaint)
            }
        );

        if (!response.ok) {
            throw new Error("Status update failed");
        }

        alert("Complaint status updated successfully!");

        // Reload the table with the latest status
        loadComplaints();

    } catch (error) {

        console.error("Error updating status:", error);
        alert("Unable to update complaint status.");

    }

}
async function deleteComplaint(id) {
    const confirmed = confirm(
        "Are you sure you want to delete this complaint? This action cannot be undone."
    );

    if (!confirmed) {
        return;
    }

    try {
        const response = await fetch(
            "http://localhost:8081/api/complaints/" + id,
            {
                method: "DELETE"
            }
        );

        if (!response.ok) {
            throw new Error("Complaint deletion failed");
        }

        alert("Complaint deleted successfully!");

        // Reload the table and update serial numbers.
        await loadComplaints();

    } catch (error) {
        console.error("Error deleting complaint:", error);
        alert(
            "Unable to delete complaint. Please check whether the backend supports DELETE."
        );
    }
}

loadComplaints();