import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import axios from "axios";
import { BASE_API_URL } from "../../config";

const initialState = {
  user: null,
  isLoading: false,
  error: null,
  jwt: null,
};

export const registerUser = createAsyncThunk(
  "user/register",
  async (user, { dispatch }) => {
    try {
      const registeredUser = await axios.post(
        `${BASE_API_URL}auth/signup`,
        user
      );
      const { jwt } = registeredUser.data;
      localStorage.setItem("jwt", jwt);
      dispatch(getUser(jwt));
      return registeredUser.data;
    } catch (err) {
      const error = {
        error: err.message,
      };
      return error;
    }
  }
);

export const loginUser = createAsyncThunk(
  "user/login",
  async (user, { dispatch }) => {
    try {
      const loginedUser = await axios.post(`${BASE_API_URL}auth/signin`, user);
      const { jwt } = loginedUser.data;
      localStorage.setItem("jwt", jwt);
      dispatch(getUser(jwt));
      return loginedUser.data;
    } catch (err) {
      const error = {
        error: err.message,
      };
      return error;
    }
  }
);

export const getUser = createAsyncThunk("user/fetchUser", async (token) => {
  try {
    console.log("Fetching user");
    const userFectched = await axios.get(`${BASE_API_URL}api/users/profile`, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
    return userFectched.data;
  } catch (err) {
    const error = {
      error: err.message,
    };
    return error;
  }
});

const authSlice = createSlice({
  name: "auth",
  initialState,
  reducers: {
    // eslint-disable-next-line no-unused-vars
    logoutReducer: (state, action) => {
      state.user = null;
      state.jwt = null;
      localStorage.removeItem("jwt");
    },
  },
  extraReducers(builder) {
    builder
      // eslint-disable-next-line no-unused-vars
      .addCase(registerUser.pending, (state, action) => {
        state.isLoading = true;
      })
      .addCase(registerUser.fulfilled, (state, action) => {
        state.jwt = action.payload.jwt;
        if (action.payload.error) {
          state.error = action.payload.error;
        } else {
          state.error = null;
        }
        state.isLoading = false;
      })
      .addCase(registerUser.rejected, (state, action) => {
        state.isLoading = false;
        state.error = action.payload;
      })
      // eslint-disable-next-line no-unused-vars
      .addCase(loginUser.pending, (state, action) => {
        state.isLoading = true;
      })
      .addCase(loginUser.fulfilled, (state, action) => {
        state.jwt = action.payload.jwt;
        if (action.payload.error) {
          state.error = action.payload.error;
        } else {
          state.error = null;
        }
        state.isLoading = false;
      })
      .addCase(loginUser.rejected, (state, action) => {
        state.error = action.payload;
        state.isLoading = false;
      })
      // eslint-disable-next-line no-unused-vars
      .addCase(getUser.pending, (state, _action) => {
        state.isLoading = true;
      })
      .addCase(getUser.fulfilled, (state, action) => {
        // console.log("User fetched using jwt => " + action.payload);
        state.user = action.payload;
        if (action.payload.error) {
          state.error = action.payload.error;
        } else {
          state.error = null;
        }
        state.isLoading = false;
      })
      .addCase(getUser.rejected, (state, action) => {
        state.error = action.payload;
        state.isLoading = false;
      });
  },
});

export const { logoutReducer } = authSlice.actions;
export default authSlice.reducer;
