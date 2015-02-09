function onLoad() {

	$("#password").keyup(checkPasswordsMatch);
	$("#passwordConfirmation").keyup(checkPasswordsMatch);

	$("#details").submit(canSubmit);
}

function canSubmit() {
	var password = $("#password").val();
	var passwordConfirmation = $("#passwordConfirmation").val();
	
	if(password != passwordConfirmation) {
		alert("Passwords do not match!")
		return false;
	}
	else {
		return true;
	}
}

function checkPasswordsMatch() {
	var password = $("#password").val();
	var passwordConfirmation = $("#passwordConfirmation").val();

	if (password.length > 3 || passwordConfirmation.length > 3) {

		if (password == passwordConfirmation) {
			$("#matchpass").text("Passwords match.");
			$("#matchpass").addClass("valid");
			$("#matchpass").removeClass("error");
		} else {
			$("#matchpass").text("Passwords do not match.");
			$("#matchpass").addClass("error");
			$("#matchpass").removeClass("valid");
		}
	}
}

$(document).ready(onLoad);