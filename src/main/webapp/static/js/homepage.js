

			function reqList(adminEmail, constPath) {
			    console.log("Request button is clicked with adminEmail: " + adminEmail);
				const element = document.getElementById('friendList');
				       
				       // Check the current display value and toggle
				       if (element.style.display === "none" || element.style.display === "") {
				           element.style.display = "block";  // Show the element
				       
			    
			    fetch(`${constPath}/getReqList?adminEmail=${adminEmail}`)  // Use the contextPath
			        .then(response => {
			            if (!response.ok) {
			                throw new Error("Error received from server");
			            }
			            return response.json();  // Parse JSON from the response
			        })
			        .then(data => {
			            const friendListDiv = document.getElementById('friendList');
			            friendListDiv.innerHTML = '';  // Clear existing content

			            if (Object.keys(data).length === 0) {
			                console.log("NO requests..");
			                friendListDiv.innerHTML = "<p>You have no requests</p>";
			            } else {
			                // Use Object.entries() to convert the object into an array of [key, value] pairs
			                Object.entries(data).forEach(([key, user]) => {
			                    // Create a new div for each friend request
			                    const requestDiv = document.createElement('div');
			                    requestDiv.className = 'requestItem'; // Apply CSS class
			                    
			                    // Create and populate <p> elements for each item
			                    const nameP = document.createElement('p');
			                    nameP.className = 'name'; // Apply CSS class
			                    nameP.innerText = `${user.firstname} ${user.lastname}`;  // Display user's name
			                    
			                    const ageP = document.createElement('p');
			                    ageP.className = 'age'; // Apply CSS class
			                    ageP.innerText = `Age: ${user.age}`;  // Display user's age
			                    
			                    const timeP = document.createElement('p');
			                    timeP.className = 'time'; // Apply CSS class
			                    timeP.innerText = `Time: ${key}`;  // Display the key (assumed to be a timestamp)

			                    // Append <p> elements to the new div
			                    requestDiv.appendChild(nameP);
			                    requestDiv.appendChild(ageP);
			                    requestDiv.appendChild(timeP);
			                    
			                    // Append the new div to the friendList div
			                    friendListDiv.appendChild(requestDiv);
			                });
			            }
			        })
			        .catch(error => {
			            console.error("Error:", error);
			            document.getElementById('friendList').innerHTML = "<p>Failed to load requests</p>";
			        });
					} else {
									           element.style.display = "none";   // Hide the element
									       }
			}
			
//sending friend request...		
function sendRequest(adminEmail, receiver, time, contextpath) {
	window.alert("Friend Request Sent")
    const queryParams = new URLSearchParams({
        admin: adminEmail,
        rec: receiver,
        t: time
    }).toString();

    fetch(`${contextpath}/sendRequest?${queryParams}`, {
        method: 'POST'
    })
    .then(response => {
        if (!response.ok) {
            return response.text().then(text => { throw new Error(text) });
        }
		const button = document.querySelector(`button[data-email="${receiver}"]`);
		       if (button) {
		           button.innerText = "Sent";
		       }
        return response.json();
		
    })
    .then(data => console.log('Success:', data))
    .catch(error => console.error('Error:', error));
}
	



	
			
			
			
        

