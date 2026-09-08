import { Link } from "react-router-dom";
import logo from "../assets/logo.svg";
import { useEffect, useRef, useState } from "react";
import { useLogout } from "../hooks/useAuth";
import { useUser } from "../hooks/useUser";
import { FaRegUserCircle } from "react-icons/fa";

function Header() {
  const ref = useRef<HTMLDivElement>(null);
  const [showDropdown, setShowDropdown] = useState(false);
  const logout = useLogout();
  const { data: user, isLoading } = useUser();

  useEffect(() => {
    if (!showDropdown) return;

    const handleClickOutside = (e: MouseEvent) => {
      if (ref.current && !ref.current.contains(e.target as Node)) {
        setShowDropdown(false);
      }
    };

    document.addEventListener("mousedown", handleClickOutside);
    return () => {
      document.removeEventListener("mousedown", handleClickOutside);
    };
  }, [showDropdown]);

  return (
    <nav
      className={`w-full z-10 top-0 pt-4 pb-2 px-10 border-b border-gray-500/60 max-w-7xl mx-auto`}
    >
      <div className="flex flex-row items-center justify-between">
        <Link to="/books">
          <div className="group flex items-center gap-2">
            <img
              className="group-hover:rotate-0 h-10 -rotate-10 transition-all duration-300"
              src={logo}
              alt="BookClub Logo"
            />
            <span className="group-hover:text-white/85 transition-all text-white duration-300 text-3xl font-logo mt-2">
              BookClub
            </span>
          </div>
        </Link>

        {/* Dropdown Menu - Profile */}
        <div className="flex items-center gap-6 relative" ref={ref}>
          <button
            onClick={() => setShowDropdown(!showDropdown)}
            className=" hover:opacity-60 hover:scale-105 transition-transform cursor-pointer "
            aria-label="User menu"
          >
            <FaRegUserCircle
            color="white" 
            size="40px"/>
          </button>

          <div
            className={`opacity-0 ${showDropdown && "opacity-100 transition-opacity"} overflow-hidden absolute flex-col items-center  w-fit left-1/2 -translate-x-1/2 bg-gray-700 rounded top-[calc(100%+10px)]`}
          >
            <div className="flex flex-col border-b border-gray-600 px-4 py-2">
              <p className="text-gray-200/70 text-sm">Signed in as</p>
              <p className="text-gray-200 text-sm">
                {isLoading ? "..." : user?.email}
              </p>
            </div>
            <p className="cursor-pointer text-gray-200/70 text-sm border-b hover:bg-gray-100/10 border-gray-600 px-4 py-2">
              Account settings
            </p>
            <button
              onClick={logout}
              className="w-full text-left cursor-pointer text-gray-200/70 hover:bg-gray-100/10 text-sm px-4 py-2"
            >
              Log out
            </button>
          </div>
        </div>
      </div>
    </nav>
  );
}

export default Header;
