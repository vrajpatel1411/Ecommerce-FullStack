import { Grid } from "@mui/material";
import OrderCard from "./OrderCard";
import { useDispatch, useSelector } from "react-redux";
import { useEffect } from "react";
import { userOrderHistory } from "../../../redux/Order/orderSlice";

const orderStatus = [
  {
    label: "on the way",
    value: "on_the_way",
  },
  {
    label: "Delivered",
    value: "delivered",
  },
  {
    label: "Cancelled",
    value: "cancelled",
  },
  {
    label: "return",
    value: "return",
  },
];
const Order = () => {
  const dispatch = useDispatch();

  const { orders } = useSelector((state) => state.orderReducer);

  useEffect(() => {
    dispatch(userOrderHistory());
  }, []);

  console.log(orders);
  return (
    <div className="px:5 lg:px-20 py-5">
      <Grid
        container
        spacing={1}
        sx={{ justifyContent: "space-between" }}>
        <Grid
          item
          xs={2.5}>
          <div className="h-auto shadow-lg bg-white p-5 sticky top-5">
            <h1 className="font-bold text-lg">Filter</h1>
            <div className="space-y-4 mt-10">
              <h1 className="font-semibold ">Order Status</h1>

              {orderStatus.map((option, index) => {
                return (
                  <div
                    key={index}
                    className="flex items-center">
                    <input
                      //   defaultValue={option.value}
                      type="checkbox"
                      className="h-4 w-4 border-gray-300 text-indigo-600 focus:ring-indigo-500"
                    />
                    <label
                      className="ml-3 text-sm text-gray-600 "
                      htmlFor={option.value}>
                      {option.label}
                    </label>
                  </div>
                );
              })}
            </div>
          </div>
        </Grid>
        <Grid
          item
          xs={9}
          className="space-y-5">
          {orders.map((item, index) => (
            <OrderCard
              key={index}
              item={item}
            />
          ))}
        </Grid>
      </Grid>
    </div>
  );
};

export default Order;
