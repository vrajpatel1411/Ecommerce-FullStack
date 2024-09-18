import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import { api } from "../../config";

export const findProductById = createAsyncThunk(
  "product/findById",
  async (id) => {
    try {
      const productData = await api.get(`/api/products/id/${id}`);
      return productData.data;
    } catch (err) {
      const error = {
        error: err.message,
      };
      return error;
    }
  }
);

export const findProducts = createAsyncThunk(
  "product/findProducts",
  async (reqData) => {
    const {
      colors,
      sizes,
      minPrice,
      maxPrice,
      minDiscount,
      category,
      stock,
      sort,
      pageNumber,
      pageSize,
    } = reqData;
    try {
      const data = await api.get(
        `/api/products?colors=${colors}&size=${sizes}&minPrice=${minPrice}&maxPrice=${maxPrice}&minDiscount=${minDiscount}&category=${category}&stock=${stock}&sort=${sort}&pageNumber=${pageNumber}&pageSize=${pageSize}`
      );
      console.log("Product Data -> ", data);
      return data.data;
    } catch (err) {
      const error = {
        error: err.message,
      };
      return error;
    }
  }
);

const initialState = {
  products: [],
  product: null,
  loading: false,
  error: null,
};

const productSlice = createSlice({
  name: "product",
  initialState,
  reducers: {},
  extraReducers(builder) {
    builder
      .addCase(findProductById.pending, (state) => {
        state.loading = true;
      })
      .addCase(findProductById.fulfilled, (state, action) => {
        state.product = action.payload;
        state.error = null;
        state.loading = false;
      })
      .addCase(findProductById.rejected, (state, action) => {
        state.error = action.payload.error;
        state.loading = false;
      })
      .addCase(findProducts.pending, (state) => {
        state.loading = true;
      })
      .addCase(findProducts.fulfilled, (state, action) => {
        state.products = action.payload;
        state.error = null;
        state.loading = false;
      })
      .addCase(findProducts.rejected, (state, action) => {
        state.error = action.payload.error;
        state.loading = false;
      });
  },
});

export default productSlice.reducer;
