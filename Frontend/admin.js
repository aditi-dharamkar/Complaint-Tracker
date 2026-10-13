const adminLoginForm=document.getElementById("adminLoginForm");
adminLoginForm.addEventListener("submit",async function(event){
    event.preventDefault();
    const email=document.getElementById("adminEmail").value;
    const password=document.getElementById("adminPassword").value;

    const admin={
        email:email,
        password:password
    };
    try{
        const response=await fetch("http://localhost:8081/api/admin/login",{
            method:"POST",
            headers:{
                "Content-Type":"application/json"
            },
            body:JSON.stringify(admin)
        });
        const result=await response.text();
        if(response.ok && result==="Login successful"){
            alert("Login successful");
            window.location.href="dashboard.html";
        }else{
            alert("Invalid email or password.");
        }
    }catch(error){
        console.error("Login error:",error);
        alert("Unable to connect to the server.");
    }
});