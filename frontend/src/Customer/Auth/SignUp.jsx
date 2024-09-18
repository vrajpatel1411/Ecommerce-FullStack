import { Button, Grid, TextField } from "@mui/material";

import { useNavigate } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import { registerUser } from "../../redux/Auth/authSlice";
import { useEffect } from "react";
const SignUp = ({ close }) => {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const userData = useSelector((state) => state.authReducer);
  const handleSubmit = (event) => {
    event.preventDefault();
    const data = new FormData(event.currentTarget);
    const user = {
      firstName: data.get("firstName"),
      lastName: data.get("lastName"),
      email: data.get("email"),
      password: data.get("password"),
    };
    dispatch(registerUser(user));
  };

  useEffect(() => {
    if (userData.error == null && userData.jwt != null) {
      close(); // Assuming this function is available in the component
      navigate("/"); // Assuming you have access to navigate function (e.g., from react-router)
    }
  }, [userData]);
  return (
    <div>
      {/* <p ></p> */}
      <p className="p-1 my-1 text-red-600 font-semibold text-base">
        {userData.error ? "* Invalid Credentials" : null}
      </p>
      <form onSubmit={handleSubmit}>
        <Grid
          container
          spacing={3}>
          <Grid
            item
            xs={12}
            sm={6}>
            <TextField
              required
              id="firstName"
              name="firstName"
              label="First Name"
              fullWidth
              autoComplete="given-name"
            />
          </Grid>
          <Grid
            item
            xs={12}
            sm={6}>
            <TextField
              required
              id="lastName"
              name="lastName"
              label="Last Name"
              fullWidth
              autoComplete="given-name"
            />
          </Grid>
          <Grid
            item
            xs={12}>
            <TextField
              required
              id="email"
              name="email"
              label="Email"
              fullWidth
              autoComplete="email"
            />
          </Grid>
          <Grid
            item
            xs={12}>
            <TextField
              type="password"
              required
              id="password"
              name="password"
              label="Password"
              fullWidth
              autoComplete="password"
            />
          </Grid>
          <Grid
            item
            xs={12}>
            <Button
              className=" w-full "
              type="submit"
              variant="contained"
              size="large"
              sx={{
                padding: "0.8rem 0",
                bgcolor: "#9155FD",
                ":hover": {
                  bgcolor: "#a374f7",
                },
              }}>
              Register
            </Button>
          </Grid>
        </Grid>
      </form>
      <div className="flex justify-center">
        <div className="py-3 flex  items-center">
          <p> If you have already account?</p>
          <Button
            onClick={() => navigate("/login")}
            className="ml-5 "
            size="small">
            Login
          </Button>
        </div>
      </div>
    </div>
  );
};

export default SignUp;
