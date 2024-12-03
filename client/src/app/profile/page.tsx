'use client'

import { useState } from 'react'
import { ProfileDisplay } from '@/components/cards/ProfileDisplay'
import { ProfileEdit } from '@/components/forms/ProfileEdit'
import { Button } from '@/components/ui/button'

interface UserProfile {
    id: string
    username: string
    email: string
    startedPlaying: number
    position: string
    teams: string[]
    events: {
        past: string[]
        upcoming: string[]
    }
}

// Mock user profile data
const mockProfile: UserProfile = {
    id: "user123",
    username: "discmaster",
    email: "discmaster@example.com",
    startedPlaying: 2015,
    position: "Handler",
    teams: ["Disc Dynamos", "City Flyers"],
    events: {
        past: ["Summer Ultimate Tournament 2022", "Spring League 2023"],
        upcoming: ["Fall Cup 2023", "Winter Indoor Series 2023"]
    }
}

export default function ProfilePage() {
    const [profile, setProfile] = useState<UserProfile>(mockProfile)
    const [isEditing, setIsEditing] = useState(false)
    const [isSaving, setIsSaving] = useState(false)

    const handleSave = async (updatedProfile: UserProfile) => {
        setIsSaving(true)
        // Simulate an API call delay
        await new Promise(resolve => setTimeout(resolve, 1000))
        setProfile(updatedProfile)
        setIsEditing(false)
        setIsSaving(false)
    }

    return (
        <div className="container mx-auto py-8">
            <h1 className="text-3xl font-bold mb-8">Player Profile</h1>
            {isEditing ? (
                <ProfileEdit
                    profile={profile}
                    onSave={handleSave}
                    onCancel={() => setIsEditing(false)}
                    isSaving={isSaving}
                />
            ) : (
                <ProfileDisplay profile={profile} setIsEditing={setIsEditing} />
            )}
        </div>
    )
}


// 'use client';

// import { useUser, withPageAuthRequired } from '@auth0/nextjs-auth0/client';

// function Profile() {
//     const { user, isLoading, error } = useUser();

//     if (isLoading) <Loading />
//     if (error) <div>{error.message}</div>

//     return (
//         <>
//             {
//                 user &&
//                 <section className="pt-5 px-5">
//                     <div>
//                         <img
//                             src={user.picture || ""}
//                             alt="Profile"
//                             className="rounded-full mb-3 mb-md-0"
//                         />
//                         <h2>{user.name}</h2>
//                         <p className="lead text-muted">
//                             {user.email}
//                         </p>
//                         <div className='w-5/12 bg-gray-800 p-3 leading-8'>
//                             <code className='text-white'>{JSON.stringify(user, null, 2)}</code>
//                         </div>
//                     </div>
//                 </section>
//             }
//         </>
//     );
// }

// export default withPageAuthRequired(Profile);


// const Loading = () => (
//     <div className="spinner d-flex align-items-center justify-content-center w-100 h-100" data-testid="loading">
//         <svg
//             xmlns="http://www.w3.org/2000/svg"
//             width="120"
//             height="120"
//             className="uil-ring"
//             preserveAspectRatio="xMidYMid"
//             viewBox="0 0 100 100">
//             <path fill="none" d="M0 0H100V100H0z" className="bk"></path>
//             <defs>
//                 <filter id="uil-ring-shadow" width="300%" height="300%" x="-100%" y="-100%">
//                     <feOffset in="SourceGraphic" result="offOut"></feOffset>
//                     <feGaussianBlur in="offOut" result="blurOut"></feGaussianBlur>
//                     <feBlend in="SourceGraphic" in2="blurOut"></feBlend>
//                 </filter>
//             </defs>
//             <path
//                 fill="#337ab7"
//                 d="M10 50s0 .5.1 1.4c0 .5.1 1 .2 1.7 0 .3.1.7.1 1.1.1.4.1.8.2 1.2.2.8.3 1.8.5 2.8.3 1 .6 2.1.9 3.2.3 1.1.9 2.3 1.4 3.5.5 1.2 1.2 2.4 1.8 3.7.3.6.8 1.2 1.2 1.9.4.6.8 1.3 1.3 1.9 1 1.2 1.9 2.6 3.1 3.7 2.2 2.5 5 4.7 7.9 6.7 3 2 6.5 3.4 10.1 4.6 3.6 1.1 7.5 1.5 11.2 1.6 4-.1 7.7-.6 11.3-1.6 3.6-1.2 7-2.6 10-4.6 3-2 5.8-4.2 7.9-6.7 1.2-1.2 2.1-2.5 3.1-3.7.5-.6.9-1.3 1.3-1.9.4-.6.8-1.3 1.2-1.9.6-1.3 1.3-2.5 1.8-3.7.5-1.2 1-2.4 1.4-3.5.3-1.1.6-2.2.9-3.2.2-1 .4-1.9.5-2.8.1-.4.1-.8.2-1.2 0-.4.1-.7.1-1.1.1-.7.1-1.2.2-1.7.1-.9.1-1.4.1-1.4V54.2c0 .4-.1.8-.1 1.2-.1.9-.2 1.8-.4 2.8-.2 1-.5 2.1-.7 3.3-.3 1.2-.8 2.4-1.2 3.7-.2.7-.5 1.3-.8 1.9-.3.7-.6 1.3-.9 2-.3.7-.7 1.3-1.1 2-.4.7-.7 1.4-1.2 2-1 1.3-1.9 2.7-3.1 4-2.2 2.7-5 5-8.1 7.1L70 85.7c-.8.5-1.7.9-2.6 1.3l-1.4.7-1.4.5c-.9.3-1.8.7-2.8 1C58 90.3 53.9 90.9 50 91l-3-.2c-1 0-2-.2-3-.3l-1.5-.2-.7-.1-.7-.2c-1-.3-1.9-.5-2.9-.7-.9-.3-1.9-.7-2.8-1l-1.4-.6-1.3-.6c-.9-.4-1.8-.8-2.6-1.3l-2.4-1.5c-3.1-2.1-5.9-4.5-8.1-7.1-1.2-1.2-2.1-2.7-3.1-4-.5-.6-.8-1.4-1.2-2-.4-.7-.8-1.3-1.1-2-.3-.7-.6-1.3-.9-2-.3-.7-.6-1.3-.8-1.9-.4-1.3-.9-2.5-1.2-3.7-.3-1.2-.5-2.3-.7-3.3-.2-1-.3-2-.4-2.8-.1-.4-.1-.8-.1-1.2v-1.1-1.7c-.1-1-.1-1.5-.1-1.5z"
//                 filter="url(#uil-ring-shadow)">
//                 <animateTransform
//                     attributeName="transform"
//                     dur="1s"
//                     from="0 50 50"
//                     repeatCount="indefinite"
//                     to="360 50 50"
//                     type="rotate"></animateTransform>
//             </path>
//         </svg>
//     </div>
// );