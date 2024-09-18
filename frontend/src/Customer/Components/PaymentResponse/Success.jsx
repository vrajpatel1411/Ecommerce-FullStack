import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { deleteCart } from "../../../redux/Cart/cartSlice";
import CheckCircleOutlineIcon from "@mui/icons-material/CheckCircleOutline";
import { Link } from "@mui/material";
import { useParams } from "react-router-dom";
export const Success = () => {
  const dispatch = useDispatch();

  const { cart } = useSelector((state) => state.cartReducer);
  const param = useParams();

  useEffect(() => {
    const data = {
      cartId: cart?.id,
    };

    dispatch(deleteCart(data));
  }, []);
  // delete item from the cart;
  return (
    <div className="mx-auto my-2 w-80 h-96 border-2 rounded-lg py-10 px-2 flex flex-col space-y-4 justify-center items-center">
      <div className="">
        <CheckCircleOutlineIcon
          sx={{ fontSize: "100px" }}
          className=" text-green-500"
        />
      </div>
      <div className="flex flex-col justify-between items-center space-y-4">
        <p className=" font-serif font-semibold text-base">
          Your Order Placed Successfully
        </p>
        <p className="font-serif text-base">
          You can checkout your order details{" "}
          <Link href={`http://localhost:5173/account/order/${param.orderId}`}>
            here
          </Link>
        </p>
      </div>
    </div>
  );
};
