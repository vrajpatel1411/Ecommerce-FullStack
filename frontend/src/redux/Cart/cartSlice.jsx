import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import { api } from "../../config";

export const addToCart = createAsyncThunk("cart/AddToCart", async (cart) => {
  try {
    const cartAdded = await api.put("/api/cart/add", cart);

    return cartAdded.data;
  } catch (err) {
    const error = {
      error: err.message,
    };
    return error;
  }
});

export const getCart = createAsyncThunk("cart/getCart", async () => {
  try {
    const cartData = await api.get("/api/cart/");
    // console.log(cartData.data);
    return cartData.data;
  } catch (err) {
    const error = {
      error: err.message,
    };
    return error;
  }
});

export const deleteCart = createAsyncThunk("cart/deleteCart", async () => {
  try {
    const res = await api.delete("/api/cart/delete");

    return res.data;
  } catch (err) {
    const error = {
      error: err.message,
    };
    console.log(error);
    return error;
  }
});

export const updateCartItem = createAsyncThunk(
  "cart/UpdateCartItem",
  async (cart) => {
    try {
      const res = await api.put(
        `/api/cart_items/${cart.cartItemId}`,
        cart.data
      );

      return res.data;
    } catch (err) {
      const error = {
        error: err.message,
      };
      return error;
    }
  }
);

export const deleteCartItem = createAsyncThunk(
  "cart/DeleteCartItem",
  async (cart) => {
    try {
      const res = await api.delete(`/api/cart_items/${cart.cartItemId}`);
      return { ...res.data, id: cart.cartItemId };
    } catch (err) {
      const error = {
        error: err.message,
      };
      return error;
    }
  }
);

const initialState = {
  cartItems: [],
  error: null,
  cart: null,
  loading: false,
};

const cartSlice = createSlice({
  name: "cart",
  initialState,
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(addToCart.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(addToCart.fulfilled, (state, action) => {
        state.cartItems = action.payload.cartItems;
        state.loading = false;
      })
      .addCase(addToCart.rejected, (state, action) => {
        state.error = action.payload.error;
        state.loading = false;
      })
      .addCase(getCart.pending, (state) => {
        state.loading = true;
      })
      .addCase(getCart.fulfilled, (state, action) => {
        state.cartItems = action.payload.cartItems;
        state.cart = action.payload;
        state.loading = false;
      })
      .addCase(getCart.rejected, (state, action) => {
        state.error = action.payload.error;
        state.loading = false;
      })
      .addCase(updateCartItem.pending, (state) => {
        state.loading = true;
      })
      .addCase(updateCartItem.fulfilled, (state, action) => {
        state.cart = action.payload;
        state.loading = false;
      })
      .addCase(updateCartItem.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload;
      })
      .addCase(deleteCartItem.pending, (state) => {
        state.loading = true;
      })
      .addCase(deleteCartItem.fulfilled, (state, action) => {
        state.cart = null;
        state.loading = false;
      })
      .addCase(deleteCartItem.rejected, (state, action) => {
        state.error = action.payload.error;
        state.loading = false;
      });
  },
});

export default cartSlice.reducer;
