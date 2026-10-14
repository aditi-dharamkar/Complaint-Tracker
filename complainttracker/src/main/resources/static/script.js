const complaintForm =document.getElementById("complaintForm");
complaintForm.addEventListener("submit",async function(event) {
    event.preventDefault();
    const complaint={
        name:document.getElementById("name").value,
        email:document.getElementById("Email").value,
        category:document.getElementById("category").value,
        description:document.getElementById("description").value,
    };
    try{
        const response=await fetch("/api/complaints",{
            method:"POST",
            headers:{
                "Content-Type":"application/json"
            },
            body:JSON.stringify(complaint)
        });
        if(response.ok){
            const data=await response.json();
            alert("Complaint submitted Successfully!\n" +
        "Your Complaint ID is: " + data.id +
        "\nPlease save this ID to track your complaint.");
            console.log("new complaint:",data);
            complaintForm.reset();
            loadComplaints();
        }else{
            alert("Failed to submit complaint.");
        }
    }catch(error){
        console.error("Error submitting complaint:",error);
        alert("Unable to connect to the server.");
    }
});

async function loadComplaints() {
    try {
        const response = await fetch(
            "/api/complaints/public"
        );

        if (!response.ok) {
            throw new Error("Failed to fetch complaints");
        }

        const complaints = await response.json();
        const tableBody = document.getElementById("complaintsTableBody");

        tableBody.innerHTML = "";

        complaints.forEach(function (complaint, index) {
            const row = document.createElement("tr");

            row.innerHTML =
                "<td>" + (index + 1) + "</td>" +
                "<td>" + complaint.id + "</td>" +
                "<td>" + complaint.category + "</td>" +
                "<td>" + complaint.status + "</td>" +
                "<td>" + complaint.createdAt + "</td>";

            tableBody.appendChild(row);
        });
    } catch (error) {
        console.error("Error loading complaints:", error);
    }
}
document.getElementById("complaintsTableBody").addEventListener(
    "click",
    async function (event) {

        if (!event.target.classList.contains("delete-btn")) {
            return;
        }

        const complaintId = event.target.dataset.id;

        const confirmed = confirm(
            "Are you sure you want to delete this complaint?"
        );

        if (!confirmed) {
            return;
        }

        try {
            const response = await fetch(
                "/api/complaints" + complaintId,
                {
                    method: "DELETE"
                }
            );

            if (response.ok) {
                alert("Complaint deleted successfully!");
                loadComplaints();
            } else {
                alert("Failed to delete complaint.");
            }

        } catch (error) {
            console.error("Error deleting complaint:", error);
            alert("Unable to connect to the server.");
        }
    }
);
loadComplaints();
setInterval(loadComplaints, 10000);