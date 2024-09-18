import { Box, Grid } from "@mui/material";
import AddressCard from "../AddressCard/AddressCard";
import OrderTracking from "./OrderTracking";
import { deepPurple } from "@mui/material/colors";
import { StarBorder } from "@mui/icons-material";
import { useParams } from "react-router-dom";
import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { getOrdersById } from "../../../redux/Order/orderSlice";
const OrderDetails = () => {
  const param = useParams();
  const dispatch = useDispatch();
  const { order } = useSelector((state) => state.orderReducer);
  const orderStatus = {
    PLACED: 1,
    CONFIRMED: 2,
    SHIPPED: 3,
    DELIVERED: 5,
  };
  useEffect(() => {
    dispatch(getOrdersById(param.orderId));
  }, []);
  return (
    <div className="px-5 lg:px-20 py-5">
      <div>
        <h1 className="font-semibold text-xl py-7">Delivery Address</h1>
        <AddressCard address={order?.shippingAddress} />
        <p className="space-y-3">
          Payment Reference Id :{" "}
          {order?.paymentDetails?.stripePaymentLinkReferenceId}
        </p>
      </div>
      <div className="py-20">
        <OrderTracking activeStep={orderStatus[order?.orderStatus]} />
      </div>
      <Grid
        container
        className="space-y-5">
        {order?.orderItems.map((i, index) => (
          <Grid
            key={index}
            item
            container
            className="shadow-xl space-y-5 rounded-md p-5 border"
            sx={{ alignItems: "center", justifyContent: "space-between" }}>
            <Grid
              item
              xs={6}>
              <div className="flex items-center space-x-4">
                <img
                  className="w-[5rem] h-[5rem] object-cover object-top"
                  src={i.product.imageUrl}></img>
                <div className="space-y-2 ml-5">
                  <p className="font-semibold ">{i.product.title}</p>
                  <p className="space-x-2 opacity-50 text-sm">
                    {" "}
                    <span>Color: {i.product.color}</span>{" "}
                    <span>Size: {i.size}</span>
                  </p>
                  <p>{i.product.brand}</p>
                  <p>$ {i.discountedPrice}</p>
                </div>
              </div>
            </Grid>
            <Grid item>
              <Box sx={{ color: deepPurple[500] }}>
                <StarBorder
                  sx={{ fontSize: "2rem" }}
                  className="px-2 text-5xl"
                />
                <span> Rate & Review Product</span>
              </Box>
            </Grid>
          </Grid>
        ))}
      </Grid>
    </div>
  );
};

export default OrderDetails;
