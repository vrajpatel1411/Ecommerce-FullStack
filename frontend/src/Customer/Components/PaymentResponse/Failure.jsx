import CancelOutlinedIcon from "@mui/icons-material/CancelOutlined";
import { Link } from "@mui/material";

export const Failure = () => {
  return (
    <div className="mx-auto my-2 w-80 h-96 border-2 rounded-lg py-10 px-2 flex flex-col space-y-4 justify-center items-center">
      <div className="">
        <CancelOutlinedIcon
          sx={{ fontSize: "100px" }}
          className=" text-red-500"
        />
      </div>
      <div className="flex flex-col justify-between items-center space-y-4">
        <p className=" font-serif font-semibold text-base">
          Sorry! for Inconvinence
        </p>
        <p className="font-serif text-base">
          You can try again
          <Link href={`http://localhost:5173/cart`}> here</Link>
        </p>
      </div>
    </div>
  );
};
