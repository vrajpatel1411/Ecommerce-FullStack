import AddressCard from "../AddressCard/AddressCard";
import { Divider, Button } from "@mui/material";
import CartItem from "../Cart/CartItem";
import { useDispatch, useSelector } from "react-redux";
import { getOrdersById, getPaymentLink } from "../../../redux/Order/orderSlice";
import { useEffect } from "react";
import { useLocation } from "react-router-dom";

const OrderSummary = () => {
  const dispatch = useDispatch();
  const location = useLocation();
  const orderData = useSelector((state) => state.orderReducer);

  const handleCheckOut = () => {
    const searchParams = new URLSearchParams(location.search);
    const orderId = searchParams.get("order_id");
    dispatch(getPaymentLink(orderId));
  };
  useEffect(() => {
    const searchParams = new URLSearchParams(location.search);
    const orderId = searchParams.get("order_id");
    dispatch(getOrdersById(orderId));
  }, []);

  const address = orderData?.order?.shippingAddress;
  return (
    <div>
      <div className="p-5 shadow-lg rounded-s-md border">
        <AddressCard address={address} />
      </div>
      <div>
        <div className="mt-10">
          <div className="lg:grid grid-cols-3  relative">
            <div className="col-span-2 ">
              {orderData?.order?.orderItems.map((item, index) => (
                <CartItem
                  key={index}
                  item={item}
                />
              ))}
            </div>
            <div className="px-5 sticky top-0 h-[100vh] mt-5 lg:mt-0">
              <div className="border p-5">
                <p className="uppercase font-bold opacity-60 pb-4">
                  Price Details
                </p>
                <Divider />
                <div className="space-y-3 font-semibold">
                  <div className="flex justify-between pt-3 text-black">
                    <span>Price</span>
                    <span>${orderData?.order?.totalPrice}</span>
                  </div>
                  <div className="flex justify-between pt-3 text-black">
                    <span>Discount</span>
                    <span className="text-green-600">
                      ${orderData?.order?.discount}
                    </span>
                  </div>

                  <div className="flex justify-between pt-3 text-black">
                    <span>Delivery Charge</span>
                    <span className="text-green-600">free</span>
                  </div>
                  <Divider />
                  <div className="flex justify-between pt-3 text-black">
                    <span>Total Amount</span>
                    <span className="text-green-600">
                      ${orderData?.order?.discountedPrice}
                    </span>
                  </div>
                </div>
                <Button
                  onClick={() => handleCheckOut()}
                  className="w-full  mb-10"
                  variant="contained"
                  sx={{
                    px: "2.5rem",
                    py: "0.7rem",
                    bgcolor: "#9155fd",
                    mt: "1rem",
                    ":hover": {
                      bgcolor: "#a374f7",
                    },
                  }}>
                  CheckOut
                </Button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default OrderSummary;
