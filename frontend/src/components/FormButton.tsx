import React from "react";

interface FormButtonProps {
  children: React.ReactNode;
  onClick?: () => void;
  type?: "submit" | "button";
  attributes?: string;
}

function FormButton({ children, onClick, type, attributes }: FormButtonProps) {
  return (
   
    <button
      type={type}
      onClick={onClick}
      className={`relative px-4 py-5 rounded-xl bg-primary text-white text-xl hover:opacity-90 cursor-pointer ${attributes}`}
    >
      <span className="absolute m-px inset-0 rounded-xl border-t border-t-white/40 border-b-6 border-b-black/30" />
      {children}
    </button>
  );
}

export default FormButton;
