import { Button, Grid, TextField } from "@mui/material";
import { useNavigate } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import { loginUser } from "../../redux/Auth/authSlice";
import { useEffect } from "react";
const LoginForm = ({ close }) => {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const loginData = useSelector((state) => state.authReducer);
  const handleSubmit = async (event) => {
    event.preventDefault();
    const data = new FormData(event.currentTarget);
    const user = {
      email: data.get("email"),
      password: data.get("password"),
    };
    await dispatch(loginUser(user));
  };

  useEffect(() => {
    if (loginData.error == null && loginData.jwt != null) {
      close(); // Assuming this function is available in the component
      navigate("/"); // Assuming you have access to navigate function (e.g., from react-router)
    }
  }, [loginData]); // dependency on loginData to trigger useEffect when it updates

  return (
    <div>
      <p className="p-1 my-1 text-red-600 font-semibold text-base">
        {loginData.error ? "* Invalid Credentials" : null}
      </p>
      <form onSubmit={handleSubmit}>
        <Grid
          container
          spacing={3}>
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
              Sign In
            </Button>
          </Grid>
        </Grid>
      </form>
      <div className="flex justify-center">
        <div className="py-3 flex  items-center">
          <p> If you don&apos;t have account?</p>
          <Button
            onClick={() => navigate("/register")}
            className="ml-5"
            size="small">
            Register
          </Button>
        </div>
      </div>
    </div>
  );
};

export default LoginForm;
